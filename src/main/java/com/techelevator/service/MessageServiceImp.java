package com.techelevator.service;

import com.techelevator.dao.MessageDao;
import com.techelevator.model.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService {
    private final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImp.class);
    private final MessageDao messageDao;

    @Autowired
    public MessageServiceImp(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_LANDLORD')")
    @Override
    public Messages getMessageById(int id) {
        try {
            return messageDao.getMessageById(id);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while retrieving message by ID: {}", id, e);
            throw new IllegalStateException("Could not retrieve message by ID.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_LANDLORD')")
    @Override
    public List<Messages> getAllMessages() {
        try {
            return messageDao.getAllMessages();
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while retrieving all messages", e);
            throw new IllegalStateException("Could not retrieve messages.");
        }
    }

    @Override
    public Messages createMessage(Messages message) {
        try {
            return messageDao.addMessage(message);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while creating message", e);
            throw new IllegalArgumentException("Could not create message.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_LANDLORD')")
    @Override
    public Messages updateMessage(Messages message) {
        return messageDao.updateMessage(message);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_LANDLORD')")
    @Override
    public boolean deleteMessage(int id) {
        try {
            return messageDao.deleteMessage(id);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while deleting message with ID: {}", id, e);
            throw new IllegalArgumentException("Could not delete message.");
        }
    }

}