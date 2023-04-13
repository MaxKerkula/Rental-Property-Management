package com.techelevator.service;

import com.techelevator.dao.PaymentsDao;

public class UserServiceImp {
    private PaymentsDao paymentsDAO;

    public UserServiceImp(PaymentsDao paymentsDAO) {
        this.paymentsDAO = paymentsDAO;
    }
}
