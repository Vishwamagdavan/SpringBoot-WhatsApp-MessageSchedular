package com.project.spring.messagescheduler.service;

import com.project.spring.messagescheduler.dto.UserRequest;
import com.project.spring.messagescheduler.entity.User;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    /**
     * To save the user in the database using JDBC template update method
     * @param userRequest  object, contains - username and auth token
     * @return Optional Object if the operations valid is updated else return some exception with details
     * @throws ResourceNotFoundException error message displayed to the user
     */
    public Optional<User> addUser(UserRequest userRequest) throws ResourceNotFoundException {
        if(userRequest==null || userRequest.getUserName().isEmpty())
            throw new ResourceNotFoundException("Invalid Object");
        User user=User.build(0,userRequest.getUserName(),null,null);
        Optional<User> result = Optional.empty();
        try {
            result=userRepository.saveUser(user);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return result;
    }
}
