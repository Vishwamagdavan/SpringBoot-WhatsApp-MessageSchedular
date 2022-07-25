package com.project.spring.messagescheduler.advice;

import com.project.spring.messagescheduler.exceptions.AuthFailedException;
import com.project.spring.messagescheduler.exceptions.ErrorDetails;
import com.project.spring.messagescheduler.exceptions.InputExceptions;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = NullPointerException.class)
    public Map<String,String> handleNullPointerException(NullPointerException exception){
        Map<String,String> errorMessage=new HashMap<>();
        errorMessage.put("message",exception.getMessage());
        return errorMessage;
    }
    /**
     * Runtime Exception - Handles all the exception during runtime
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<?> handleRunTimeException(RuntimeException exception,WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * When the data fetching is fails, this method gives user with error.
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Sends the response with INTERNAL_SERVER_ERROR for other unhandled exceptions
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * handles Invalid argument passed by the user in the request body
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, DataIntegrityViolationException.class})
    public ResponseEntity<?> handleInvalidArgument(MethodArgumentNotValidException exception,WebRequest request){
        HashMap<String,String> map=new HashMap<>();
        map.put("date",new Date().toString());
        exception.getBindingResult().getFieldErrors().forEach(error->{
            map.put(error.getField(),error.getDefaultMessage());
        });
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles other input exceptions
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InputExceptions.class})
    public Map<String,String> handleInputFoundException(InputExceptions exception){
        Map<String,String> errorMessage=new HashMap<>();
        errorMessage.put("error",exception.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(AuthFailedException.class)
    public ResponseEntity<?> authException(AuthFailedException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
