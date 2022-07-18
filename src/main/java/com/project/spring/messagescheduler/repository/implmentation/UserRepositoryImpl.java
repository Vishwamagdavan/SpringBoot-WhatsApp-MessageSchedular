package com.project.spring.messagescheduler.repository.implmentation;

import com.project.spring.messagescheduler.entity.User;
import com.project.spring.messagescheduler.repository.UserRepository;
import com.project.spring.messagescheduler.utils.AuthenticationToken;
import com.sun.javafx.util.Logging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private AuthenticationToken authenticationToken;
    @Override
    public User saveUser(User user) {
        String authToken=authenticationToken.generateAuthenticationToken();
        String SQL_QUERY="INSERT INTO users (user_name,auth_token) VALUES (?,?)";
        KeyHolder holder=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps=con.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,user.getUserName());
                ps.setString(2,authToken);
                return ps;
            }
        },holder);
        int userId= Objects.requireNonNull(holder.getKey()).intValue();
        return this.findById(userId);
    }

    @Override
    public User findById(long userId) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id=?",new BeanPropertyRowMapper<User>(User.class),userId);
    }

    @Override
    public String retrieveAuthToken(long userId) {
        return (String) jdbcTemplate.queryForObject("SELECT auth_token FROM users WHERE user_id=?",String.class,userId);
    }
}
