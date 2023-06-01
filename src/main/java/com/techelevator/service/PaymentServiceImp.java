package com.techelevator.service;

import com.techelevator.model.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentServiceImp {
    public List<Payment> viewAllRent(LocalDate startDate, LocalDate endDate);
}
