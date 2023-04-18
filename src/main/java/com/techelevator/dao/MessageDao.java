package com.techelevator.dao;

import com.techelevator.model.Messages;

import java.util.List;

public interface MessageDao {
    Messages addMessage(Messages message);

    Messages getMessageById(int id);

    List<Messages> getAllMessages();

    Messages updateMessage(Messages message);

    boolean deleteMessage(int id);
}
