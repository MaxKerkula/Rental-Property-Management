package com.techelevator.dao;

import com.techelevator.model.AccountBalance;
import com.techelevator.model.Payment;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface PaymentDao {

    AccountBalance payRent(int rentPayment, int userId);

    Payment getPaymentById(int paymentId);

    List<Payment> findAllPayments();

    List<Payment> viewAllRent(LocalDate startDate, LocalDate endDate);

    void updatePayment(Payment payment);

    void deletePayment(int paymentId);

    public int createPayment(Payment payment);

    AccountBalance viewRentBill(int userId);

    public AccountBalance getAccountBalance(int userId);


    }



