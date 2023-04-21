package com.techelevator.dao;

import com.techelevator.model.Messages;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcMessageDao implements MessageDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Messages addMessage(Messages message) {
        String sql = "INSERT INTO messages (landlord_user_id, sender_email, subject, message_text, sent_at) VALUES (?, ?, ?, ?, ?) RETURNING message_id";
        int messageId = jdbcTemplate.queryForObject(sql, Integer.class, message.getLandlordUserId(), message.getSenderEmail(), message.getSubject(), message.getMessageText(), message.getSentAt());
        message.setMessageId(messageId);
        return message;
    }

    @Override
    public Messages getMessageById(int id) {
        String sql = "SELECT * FROM messages WHERE message_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);

        if (rowSet.next()) {
            return mapRowToMessage(rowSet);
        }

        return null;
    }

    @Override
    public List<Messages> getAllMessages() {
        List<Messages> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()) {
            messages.add(mapRowToMessage(rowSet));
        }

        return messages;
    }

    @Override
    public Messages updateMessage(Messages message) {
        String sql = "UPDATE messages SET landlord_user_id = ?, sender_email = ?, subject = ?, message_text = ?, sent_at = ? WHERE message_id = ?";
        jdbcTemplate.update(sql, message.getLandlordUserId(), message.getSenderEmail(), message.getSubject(), message.getMessageText(), message.getSentAt(), message.getMessageId());
        return message;
    }

    @Override
    public boolean deleteMessage(int id) {
        String sql = "DELETE FROM messages WHERE message_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    private Messages mapRowToMessage(SqlRowSet rowSet) {
        Messages message = new Messages();
        message.setMessageId(rowSet.getInt("message_id"));
        message.setLandlordUserId(rowSet.getInt("landlord_user_id"));
        message.setSenderEmail(rowSet.getString("sender_email"));
        message.setSubject(rowSet.getString("subject"));
        message.setMessageText(rowSet.getString("message_text"));
        message.setSentAt(Objects.requireNonNull(rowSet.getTimestamp("sent_at")).toLocalDateTime());
        return message;
    }
}
