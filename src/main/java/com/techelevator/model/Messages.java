package com.techelevator.model;

import java.time.LocalDateTime;


public class Messages {
    private int messageId;
    private int landlordUserId;
    private String senderEmail;
    private String subject;
    private String messageText;
    private LocalDateTime sentAt;

    public Messages(int messageId, int landlordUserId, String senderEmail, String subject, String messageText, LocalDateTime sentAt) {
        this.messageId = messageId;
        this.landlordUserId = landlordUserId;
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.messageText = messageText;
        this.sentAt = sentAt;
    }

    public Messages() {

    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getLandlordUserId() {
        return landlordUserId;
    }

    public void setLandlordUserId(int landlordUserId) {
        this.landlordUserId = landlordUserId;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}