package com.project.spring.messagescheduler.repository.implmentation;

import com.project.spring.messagescheduler.entity.Message;
import com.project.spring.messagescheduler.exceptions.ResourceNotFoundException;
import com.project.spring.messagescheduler.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private Logger logger= LoggerFactory.getLogger(MessageRepositoryImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Message request is converted into message object and then the saving done using Prepared statement with keyHolder, the keyholder gather the primary key and returns to the user
     * @param message contains message,time to send other details
     * @return Message object
     * @throws ResourceNotFoundException Throws exceptions to the user
     */
    @Override
    public Message saveMessage(Message message) throws ResourceNotFoundException {
        if(message==null) throw new NullPointerException("Message Data cannot be null");
        String SQL_QUERY="INSERT INTO message(message_content,user_id,phone_number,scheduled_at,status) VALUES(?,?,?,?,?)";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement ps= con.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,message.getMessageContent());
                ps.setLong(2,message.getUserId());
                ps.setString(3,message.getPhoneNumber());
                ps.setTimestamp(4,message.getScheduledAt());
                ps.setInt(5,message.getStatus());
                return ps;
            },keyHolder);
        }catch (DataAccessException exception){
            throw new ResourceNotFoundException("Data insertion failed");
        }
        catch (Exception exception){
            throw new RuntimeException("Something went wrong");
        }
        finally {
            long messageId= Objects.requireNonNull(keyHolder.getKey()).longValue();
            message.setMessageId(messageId);
            logger.info(message.toString());
            return message;
        }
    }

    /**
     * The message is retrieved using message ID
     * @param messageId the user sends the messageID
     * @return returns message object or else null
     */
    @Override
    public Message retrieveMessage(Long messageId) {
        Message message = null;
        try {
            message=jdbcTemplate.queryForObject("SELECT * FROM message WHERE message_id=?",new BeanPropertyRowMapper<>(Message.class),messageId);
        }catch (InvalidResultSetAccessException exception) {
            throw new RuntimeException("Something went wrong");
        }
        catch (DataAccessException exception){
            throw  new RuntimeException("Database failure");
        }
        finally {
            return message;
        }
    }

    /**
     * Method returns all the message by the user
     * @param userId userID must be valid userId
     * @return List of Message object or null
     * @throws ResourceNotFoundException throws exception and displayed to the user
     */
    @Override
    public List<Message> retrieveAllMessagesById(Long userId) throws ResourceNotFoundException {
        List<Message> result=null;
        try {
            result=jdbcTemplate.query("SELECT * FROM message WHERE user_id=?",new BeanPropertyRowMapper<>(Message.class),userId);
        }catch (DataAccessException exception){
            throw new ResourceNotFoundException("Cannot fetch message list with id:"+userId);
        }
        catch (Exception exception){
            throw new RuntimeException("Something went wrong");
        }
        finally {
            return result;
        }
    }

    /**
     * The method is used to display list of the message by the status
     * @param userId user must be valid user, else it throws an error
     * @param status status must be between 0-2
     * @return List of message or null
     * @throws ResourceNotFoundException throws exception to display in the user request object
     */
    @Override
    public List<Message> retrieveMessageByStatus(Long userId,int status) throws ResourceNotFoundException {
        List<Message> result=null;
        try {
            result=jdbcTemplate.query("SELECT * FROM message WHERE user_id=? AND status=?",new BeanPropertyRowMapper<>(Message.class),userId,status);
        }catch (DataAccessException exception){
            throw new ResourceNotFoundException("Cannot fetch message list with id:"+userId);
        }
        catch (Exception exception){
            throw new RuntimeException("Something went wrong");
        }
        finally {
            return result;
        }
    }

    /**
     * The method is used to display list of the message by the status
     * @return List of message or null
     * @throws ResourceNotFoundException throws exception to display in the user request object
     */
    @Override
    public List<Message> retrieveAllMessages() throws ResourceNotFoundException {
        List<Message> result=null;
        try {
            result=jdbcTemplate.query("SELECT * FROM message WHERE (status=0 OR status=1) AND  DATE_ADD(NOW(),INTERVAL 1 MINUTE)>scheduled_at ORDER BY scheduled_at ASC,status DESC",new BeanPropertyRowMapper<>(Message.class));
        }catch (DataAccessException exception){
            throw new ResourceNotFoundException("failed to fetching the message");
        }
        catch (Exception exception){
            throw new RuntimeException("Something went wrong");
        }
        finally {
            return result;
        }
    }

    /**
     * Updates the message in the database using JDBC template update method
     * @param message using message object, with message ID
     * @return 1 if the message are updated else 0
     */
    @Override
    public int updateStatus(Message message) {
        try {
            String sql="UPDATE message SET gupshup_message_id=?,status=?,sent_at=? WHERE message_id=?";
            return jdbcTemplate.update(sql,message.getGupshupMessageId(),message.getStatus(),message.getSentAt(),message.getMessageId());
        }
        catch (Exception exception){
            throw new RuntimeException("Failed to update the message"+exception);
        }
        finally {
            // if the update fails
            return 0;
        }
    }
}
