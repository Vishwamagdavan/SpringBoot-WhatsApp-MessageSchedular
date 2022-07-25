package com.project.spring.messagescheduler.repository.implmentation;

import com.project.spring.messagescheduler.entity.User;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.repository.UserRepository;
import com.project.spring.messagescheduler.utils.AuthenticationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    AuthenticationToken authenticationToken;
    @Override
    public Optional<User> saveUser(User user) throws ResourceNotFoundException {
        if(user==null){
            throw new NullPointerException("User object cannot be null");
        }
        String authToken=authenticationToken.generateAuthenticationToken();
        String SQL_QUERY="INSERT INTO users (user_name,auth_token) VALUES (?,?)";
        KeyHolder holder=new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement ps=con.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,user.getUserName());
                ps.setString(2,authToken);
                return ps;
            },holder);
        }
        catch (Exception exception){
            throw new RuntimeException("Problem in saving the data");
        }
        int userId= Objects.requireNonNull(holder.getKey()).intValue();
        return Optional.ofNullable(this.findById(userId));
    }

    @Override
    public User findById(long userId) throws ResourceNotFoundException {
        User result;
        try {
            result=jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id=?", new BeanPropertyRowMapper<>(User.class),userId);
        }catch (DataAccessException exception){
            throw new ResourceNotFoundException("Cannot fetch user with id:"+userId);
        }
        catch (Exception exception){
            throw new RuntimeException("Something went wrong");
        }
        return result;
    }

    @Override
    public String retrieveAuthToken(long userId) throws ResourceNotFoundException {
        String result;
        try {
            result= jdbcTemplate.queryForObject("SELECT auth_token FROM users WHERE user_id=?",String.class,userId);
        }catch (DataAccessException exception){
            throw new ResourceNotFoundException("Cannot fetch user with id:"+userId);
        }
        catch (Exception exception){
            throw new RuntimeException("Something went wrong");
        }
        return result;
    }

    @Override
    public User isValidUser(String authToken, Long userId) throws ResourceNotFoundException {
        User result;
        try {
            result=jdbcTemplate.queryForObject("SELECT * FROM users WHERE auth_token=? AND user_id=?", new BeanPropertyRowMapper<>(User.class), authToken,userId);
        }catch (DataAccessException exception){
            throw new ResourceNotFoundException("Invalid user credentials");
        }
        catch (Exception exception){
            throw new RuntimeException("Something went wrong");
        }
        return result;
    }

    @Override
    public User isValidToken(String authToken) throws ResourceNotFoundException {
        User result;
        try {
            result=jdbcTemplate.queryForObject("SELECT * FROM users WHERE auth_token=?", new BeanPropertyRowMapper<>(User.class), authToken);
        }catch (DataAccessException exception){
            throw new ResourceNotFoundException("Invalid token");
        }
        catch (Exception exception){
            throw new RuntimeException("Something went wrong");
        }
        return result;

    }
}
