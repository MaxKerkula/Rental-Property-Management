package com.techelevator.controller;

import com.techelevator.dao.PaymentDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.AccountBalance;
import com.techelevator.model.Payment;

import com.techelevator.model.Payment;

import com.techelevator.model.Property;
import com.techelevator.service.PaymentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/rent")
public class PaymentController {
    private PaymentDao paymentDao;
    private UserDao userDao;
    private PaymentService paymentService;

    public PaymentController(PaymentDao paymentDao, UserDao userDao, PaymentService paymentService) {
        this.paymentDao = paymentDao;
        this.userDao = userDao;
        this.paymentService = paymentService;
    }

    @RequestMapping(path = "/payments", method = RequestMethod.POST)
    public void payRent(@RequestBody Payment payment) {
        paymentDao.payRent(payment);
    }

    @RequestMapping(path = "/payments/viewRentBill/{userId}", method = RequestMethod.GET)
    public AccountBalance viewRentBill(@PathVariable int userId, Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        if (loggedInUserId == userId) {
            return paymentDao.viewRentBill(userId);
        }
        return null;
    }


    @GetMapping("/viewAllRent")
    public List<Payment> viewAllRent(@RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate startDate,
                                     @RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate endDate) {

        return paymentService.viewAllRent(startDate, endDate);
    }


    @RequestMapping(path = "/failedpayment", method = RequestMethod.PUT)
    public boolean deletePayment(@RequestBody Payment payment) {
        boolean isSuccess = paymentDao.deletePayment(payment);
        return isSuccess;
    }

    @RequestMapping(path = "/updateproperty", method = RequestMethod.PUT)
    public boolean updateRentalProperty(@RequestBody Property updatedProperty) {
        return paymentDao.updateRentalProperty(updatedProperty);
    }


}