package com.techelevator.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Payment {
    private int paymentId;
    @NotNull(message = "Payment date is required.")
    private LocalDate paymentDate;

    @NotNull(message = "Payment amount is required.")
    @Positive(message = "Payment amount must be positive.")
    private Integer paymentAmount;

    @NotNull(message = "User ID is required.")
    private Integer userId;

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