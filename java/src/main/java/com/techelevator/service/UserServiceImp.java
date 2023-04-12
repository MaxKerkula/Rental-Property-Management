package com.techelevator.service;

import com.techelevator.dao.PaymentsDAO;

public class UserServiceImp {
    private PaymentsDAO paymentsDAO;

    public UserServiceImp(PaymentsDAO paymentsDAO) {
        this.paymentsDAO = paymentsDAO;
    }
}
