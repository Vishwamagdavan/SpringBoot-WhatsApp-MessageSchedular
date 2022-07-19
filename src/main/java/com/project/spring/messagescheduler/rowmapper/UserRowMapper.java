package com.project.spring.messagescheduler.rowmapper;

import com.project.spring.messagescheduler.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserRowMapper implements RowMapper {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user=new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setAuthToken(rs.getString("auth_token"));
        user.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
        return user;
    }
}
