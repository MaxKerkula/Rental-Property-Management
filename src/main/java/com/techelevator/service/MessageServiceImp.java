package com.techelevator.service;

import com.techelevator.dao.MessageDao;
import com.techelevator.model.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImp implements MessageService {

    private MessageDao messageDao;

    @Autowired
    public MessageServiceImp(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public Messages getMessageById(int id) {
        return messageDao.getMessageById(id);
    }

    @Override
    public List<Messages> getAllMessages() {
        return messageDao.getAllMessages();
    }

    @Override
    public Messages createMessage(Messages message) {
        return messageDao.addMessage(message);
    }

    @Override
    public Messages updateMessage(Messages message) {
        return messageDao.updateMessage(message);
    }

    @Override
    public boolean deleteMessage(int id) {
        return messageDao.deleteMessage(id);
    }
}