package com.techelevator.service;

import com.techelevator.dao.PaymentDao;

public class UserServiceImp {
    private PaymentDao paymentDAO;

    public UserServiceImp(PaymentDao paymentDAO) {
        this.paymentDAO = paymentDAO;
    }
}
