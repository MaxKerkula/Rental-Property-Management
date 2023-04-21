package com.techelevator.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
public class Messages {
    private int messageId;

    @NotNull(message = "Landlord user ID is required.")
    private Integer landlordUserId;

    @NotBlank(message = "Sender email is required.")
    @Email(message = "Sender email must be a valid email address.")
    private String senderEmail;

    @NotBlank(message = "Subject is required.")
    private String subject;

    @NotBlank(message = "Message text is required.")
    private String messageText;

    @NotNull(message = "Sent date is required.")
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