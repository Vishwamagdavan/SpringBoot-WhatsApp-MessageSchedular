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

    /**
     * To save the user in the database using JDBC template update method
     * @param user User object, contains - username and auth token
     * @return Optional Object if the operations valid is updated else return some exception with details
     * @throws ResourceNotFoundException error message displayed to the user
     */
    @Override
    public Optional<User> saveUser(User user) throws ResourceNotFoundException {
        if(user==null){
            throw new NullPointerException("User object cannot be null");
        }
        String authToken=authenticationToken.generateAuthenticationToken();
        String SQL_QUERY="INSERT INTO users (user_name,auth_token) VALUES (?,?)";
        KeyHolder holder=new GeneratedKeyHolder();
        int userId = -1;
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement ps=con.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,user.getUserName());
                ps.setString(2,authToken);
                return ps;
            },holder);
            userId=Objects.requireNonNull(holder.getKey()).intValue();
        }
        catch (Exception exception){
            throw new RuntimeException("Problem in saving the data");
        }
        finally {
            return Optional.ofNullable(this.findById(userId));
        }
    }

    /**
     * Method used to find the User, using the userID, it is done using the JDBC template
     * @param userId used Must be valid
     * @return User object that is directly fetched from the database;
     * @throws ResourceNotFoundException Display the user with message;
     */
    @Override
    public User findById(long userId) throws ResourceNotFoundException {
        if(userId<0) {
            throw new ResourceNotFoundException("User Id cannot be null");
        }
        User result = null;
        try {
            result=jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id=?", new BeanPropertyRowMapper<>(User.class),userId);
        }catch (DataAccessException exception){
            throw new ResourceNotFoundException("Cannot fetch user with id:"+userId);
        }
        catch (Exception exception){
            throw new RuntimeException("Something went wrong");
        }
        finally {
            return result;
        }
    }

    /**
     * Checks if the user is valid for the message request sent from the client
     * @param authToken auth token from header
     * @param userId user ID and auth token must be same
     * @return true if the user is present or not
     * @throws ResourceNotFoundException display error for the user
     */
    @Override
    public User isValidUser(String authToken, Long userId) throws ResourceNotFoundException {
        User result = null;
        try {
            result=jdbcTemplate.queryForObject("SELECT * FROM users WHERE auth_token=? AND user_id=?", new BeanPropertyRowMapper<>(User.class), authToken,userId);
        }catch (DataAccessException exception){
            throw new ResourceNotFoundException("Invalid user credentials");
        }
        catch (Exception exception){
            throw new RuntimeException("Something went wrong");
        }
        finally {
            return result;
        }
    }
    /**
     * Checks for the valid auth_token header
     * @param authToken auth token from header
     * @return true if the user is present or not
     * @throws ResourceNotFoundException display error for the user
     */
    @Override
    public User isValidToken(String authToken) throws ResourceNotFoundException {
        User result=null;
        try {
            result=jdbcTemplate.queryForObject("SELECT * FROM users WHERE auth_token=?", new BeanPropertyRowMapper<>(User.class), authToken);
        }catch (DataAccessException exception){
            throw new ResourceNotFoundException("Invalid token");
        }
        catch (Exception exception){
            throw new RuntimeException("Something went wrong");
        }
        finally {
            return result;
        }
    }
}
