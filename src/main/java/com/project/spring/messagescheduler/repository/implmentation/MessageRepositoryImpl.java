package com.project.spring.messagescheduler.repository.implmentation;

import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.entity.User;
import com.project.spring.messagescheduler.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Message saveMessage(Message message) {
        String SQL_QUERY="INERT INTO messages(message_content,user_id,phone_number,scheduled_at,status,gupshup_message_id,sent_at) VALUES(?,?,?,?,?,?,?)";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps= con.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,message.getMessageContent());
                ps.setLong(2,message.getUserId());
                ps.setString(3,message.getPhoneNumber());
                ps.setTimestamp(4,message.getScheduledTime());
                ps.setString(5, message.getGupshupMessageId());
                ps.setTimestamp(6,message.getSentTime());
                return ps;
            }
        },keyHolder);
        Long messageId= Objects.requireNonNull(keyHolder.getKey()).longValue();
        return this.retrieveMessage(messageId);
    }

    @Override
    public Message retrieveMessage(Long messageId) {
        return jdbcTemplate.queryForObject("SELECT * FROM messages WHERE message_id=?",new BeanPropertyRowMapper<Message>(Message.class),messageId);
    }

    @Override
    public List<Message> retrieveAllMessages(Long userId) {
        return jdbcTemplate.query("SELECT * FROM messages WHERE user_id=?",new BeanPropertyRowMapper<>(Message.class),userId);
    }

    @Override
    public List<Message> retrieveMessageByStatus(Long userId,int status) {
        return jdbcTemplate.query("SELECT * FROM messages WHERE user_id=? AND status=?",new BeanPropertyRowMapper<>(Message.class),userId,status);
    }
}
