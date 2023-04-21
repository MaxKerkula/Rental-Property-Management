package com.techelevator.service;

import com.techelevator.dao.PaymentDao;
import com.techelevator.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PaymentServiceImp implements PaymentService {
    private PaymentDao paymentDao;

    @Autowired
    public PaymentServiceImp(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }
    @Override


    public List<Payment> viewAllRent(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate start = LocalDate.parse(startDate.format(formatter), formatter);
        LocalDate end = LocalDate.parse(endDate.format(formatter), formatter);
        return paymentDao.viewAllRent(start, end);
    }



}