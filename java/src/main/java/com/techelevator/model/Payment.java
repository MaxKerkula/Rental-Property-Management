package com.techelevator.model;

import java.time.LocalDate;


public class Payment {
    private int paymentId;
    private LocalDate paymentDate;
    private int paymentAmount;
    private int userId;

    public Payment() {
    }

    public Payment(int paymentId, LocalDate paymentDate, int paymentAmount, int userId) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.userId = userId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}