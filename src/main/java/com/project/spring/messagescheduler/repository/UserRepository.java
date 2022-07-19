package com.project.spring.messagescheduler.repository;

import com.project.spring.messagescheduler.entity.User;

public interface UserRepository {
    User saveUser(User user);
    User findById(long userId);
    String retrieveAuthToken(long userId);

    boolean isValidUser(String authToken, Long userId);
}
