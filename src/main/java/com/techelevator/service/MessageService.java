package com.techelevator.service;

import com.techelevator.model.Messages;

import java.util.List;

public interface MessageService {
    Messages getMessageById(int id);

    List<Messages> getAllMessages();

    Messages createMessage(Messages message);

    Messages updateMessage(Messages message);

    boolean deleteMessage(int id);
}