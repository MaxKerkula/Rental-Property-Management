package com.techelevator.dao;

import com.techelevator.model.AccountBalance;
import com.techelevator.model.Payment;
import com.techelevator.model.Property;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface PaymentDao {

    AccountBalance payRent(Payment payment);

    Payment getPaymentById(int paymentId);

    List<Payment> findAllPayments();

    List<Payment> viewAllRent(LocalDate startDate, LocalDate endDate);

    boolean updateRentalProperty(Property updatedProperty);

    boolean deletePayment(Payment payment);


    AccountBalance viewRentBill(int userId);

    AccountBalance getAccountBalance(int userId);


}