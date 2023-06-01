package com.techelevator.controller;

import com.techelevator.dao.PaymentDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.AccountBalance;
import com.techelevator.model.Payment;

import com.techelevator.model.Payment;

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

    public PaymentController(PaymentDao paymentDao, UserDao userDao) {
        this.paymentDao = paymentDao;
        this.userDao = userDao;
    }


    @RequestMapping(path = "/payments", method = RequestMethod.POST)
    public void payRent(@RequestBody Payment payment) {
        paymentDao.payRent(payment.getPaymentAmount(), payment.getUserId());
    }

    //============================================================================================================================================
    @RequestMapping(path = "/payments/viewRentBill/{userId}", method = RequestMethod.GET)
    public AccountBalance viewRentBill(@PathVariable int userId, Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        if (loggedInUserId == userId) {
            return paymentDao.viewRentBill(userId);
        }
        return null;
    }
//========================================================================================================================================================

    @GetMapping("/viewAllRent")
    public List<Payment> viewAllRent(@RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate startDate,
                                     @RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate start = LocalDate.parse(startDate.format(formatter), formatter);
        LocalDate end = LocalDate.parse(endDate.format(formatter), formatter);
        return paymentDao.viewAllRent(start, end);
    }
//========================================================================================================================================================


}


















//      Required Methods for MVP and Stretch, Complete Business Logic in Service Layer:
//      GET /payments/{id}: get a payment by ID
//      GET /users/payments/{userId} get all payments by userID
//      GET /payments: get all payments
//      GET /account-balances/: get all account balances
//      GET /account-balances/{userId}: get an account balance by user ID
//      PUT /payments/{id}: update a payment
//      PUT /account-balances/{userId}: update an account balance
//      POST /payments/: create a payment
//      POST /account-balances/: add an account balance
//      DELETE /account-balances/{userId}: delete an account balance
//      DELETE /payments/{id}: delete a payment


//Service
//public class PaymentServiceImplementation implements PaymentService {
//    private PaymentDao paymentDao;
//    private PaymentDao paymentDao;
//
//    public PaymentServiceImplementation(PaymentDao paymentDao, UserDao userDao) {
//        this.paymentDao = PaymentServiceImplementation;
//    }
//@Override
//public List<Payment> viewAllRent(LocalDate startDate, LocalDate endDate) {
//    try {
//        return paymentsDao.viewAllRent(startDate, endDate);
//    } catch (DataAccessException e) {
//        return null;
//    }
//}
//
//
//}


//package com.techelevator.controller;
//
//        import com.techelevator.dao.PaymentsDAO;
//        import com.techelevator.model.Payments;
//        import org.springframework.http.HttpStatus;
//        import org.springframework.http.ResponseEntity;
//        import org.springframework.web.bind.annotation.*;
//
//        import java.time.LocalDate;
//
//@RestController
//@CrossOrigin
//public class PaymentsController {
//    private final PaymentsDAO paymentsDAO;
//
//    public PaymentsController(PaymentsDAO paymentsDAO) {
//        this.paymentsDAO = paymentsDAO;
//    }
//
//    @RequestMapping(path = "payments/{propertyId}/bill", method = RequestMethod.GET)
//    public LocalDate payments(@PathVariable int propertyId) {
//        return paymentsDAO.getRentDueDate(propertyId);
//    }
//
//    @RequestMapping(path = "payments/{userId}/payBill", method = RequestMethod.POST)
//    public void payRent(@RequestParam int rentPayment, @PathVariable int userId) {
//        paymentsDAO.payRent(rentPayment, userId);
//    }
//    @PostMapping(path = "/payments/{userId}/viewRentBill")
//    public Integer viewRentBill(@PathVariable int userId) {
//        return paymentsDAO.viewRentBill(userId);
//    }
//}
//    @RequestParam is used to extract values from the query string of a URL. This is typically used when the URL contains one or more parameters that need to be passed as input to the controller method.


//
// i need to fin a way to hard code in a date that all tenants will pay there rent on
//@RequestMapping(path = "payments/{propertyId}/bill", method = RequestMethod.GET)
//public LocalDate payments(@PathVariable int propertyId) {
//        return paymentDao.getRentDueDate(propertyId);
//        }