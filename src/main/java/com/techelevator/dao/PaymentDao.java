package com.techelevator.dao;

import com.techelevator.model.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentDao {

    Integer viewRentBill(int userId);

    int payRent(int rentPayment, int userId);

    public List<Payment> viewAllRent(LocalDate startDate, LocalDate endDate);
}