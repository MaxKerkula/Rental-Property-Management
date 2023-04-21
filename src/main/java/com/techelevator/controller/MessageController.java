package com.techelevator.controller;

import com.techelevator.model.Messages;
import com.techelevator.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Messages> getMessageById(@PathVariable int id) {
        Messages message = messageService.getMessageById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<Messages>> getAllMessages() {
        List<Messages> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Messages> createMessage(@RequestBody Messages message) {
        Messages createdMessage = messageService.createMessage(message);
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Messages> updateMessage(@PathVariable int id, @RequestBody Messages message) {
        message.setMessageId(id);
        Messages updatedMessage = messageService.updateMessage(message);
        return new ResponseEntity<>(updatedMessage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMessage(@PathVariable int id) {
        boolean deleted = messageService.deleteMessage(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}