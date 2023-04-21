package com.techelevator.service;

import com.techelevator.model.Payment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface PaymentService {
    List<Payment> viewAllRent(LocalDate startDate, LocalDate endDate);
}