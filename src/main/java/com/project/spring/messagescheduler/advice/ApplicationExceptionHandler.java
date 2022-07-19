package com.project.spring.messagescheduler.advice;

import com.project.spring.messagescheduler.exceptions.InputExceptions;
import com.project.spring.messagescheduler.exceptions.RecordNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, DataIntegrityViolationException.class})
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String,String> errors=new HashMap<>();
        exception.getFieldErrors().forEach(errorMessage->errors.put(errorMessage.getField(),errorMessage.getDefaultMessage()));
        return errors;
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {DataAccessException.class, InvalidResultSetAccessException.class})
    public Map<String,String> handleDataLayerException(DataAccessException exception){
        Map<String,String> errorMessage=new HashMap<>();
        errorMessage.put("error",exception.getMessage());
        return errorMessage;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {RecordNotFoundException.class})
    public Map<String,String> handleResourceNotFoundException(RecordNotFoundException exception){
        Map<String,String> errorMessage=new HashMap<>();
        errorMessage.put("error",exception.getMessage());
        return errorMessage;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InputExceptions.class})
    public Map<String,String> handleInputFoundException(InputExceptions exception){
        Map<String,String> errorMessage=new HashMap<>();
        errorMessage.put("error",exception.getMessage());
        return errorMessage;
    }
}
