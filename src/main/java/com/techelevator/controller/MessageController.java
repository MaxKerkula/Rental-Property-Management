package com.techelevator.controller;

import com.techelevator.model.Messages;
import com.techelevator.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    public Messages getMessageById(@PathVariable int id) {
        return messageService.getMessageById(id);
    }

    @GetMapping("")
    public List<Messages> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping("")
    public Messages createMessage(@RequestBody Messages message) {
        return messageService.createMessage(message);
    }

    @PutMapping("/{id}")
    public Messages updateMessage(@PathVariable int id, @RequestBody Messages message) {
        message.setMessageId(id);
        return messageService.updateMessage(message);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMessage(@PathVariable int id) {
        return messageService.deleteMessage(id);
    }
}