package com.project.spring.messagescheduler.repository;

import com.project.spring.messagescheduler.entity.User;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;

import javax.management.relation.RelationServiceNotRegisteredException;

public interface UserRepository {
    User saveUser(User user) throws ResourceNotFoundException;
    User findById(long userId) throws RelationServiceNotRegisteredException, ResourceNotFoundException;
    String retrieveAuthToken(long userId) throws ResourceNotFoundException;

    User isValidUser(String authToken, Long userId) throws ResourceNotFoundException;

    User isValidToken(String authToken) throws ResourceNotFoundException;
}
