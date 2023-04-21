package com.techelevator.dao;

import com.techelevator.model.AccountBalance;
import com.techelevator.model.Payment;
import com.techelevator.model.Property;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPaymentDao implements PaymentDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPaymentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public AccountBalance viewRentBill(int userId) {
        AccountBalance accountBalance = new AccountBalance();
        SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT * FROM account_balance WHERE user_id = ?",
                userId);
        if (result.next()) {
            accountBalance.setUserId(result.getInt("user_id"));
            accountBalance.setBalanceAmount(result.getInt("balance_amount"));
            accountBalance.setBalance_id(result.getInt("balance_id"));
        }
        return accountBalance;
    }

    @Transactional
    @Override
    public AccountBalance payRent(Payment payment) {
        String sql = "SELECT balance_amount FROM account_balance WHERE user_id = ?";
        String sql2 = "UPDATE account_balance SET balance_amount = ? WHERE user_id = ?";
        String sql3 = "INSERT INTO payment (payment_date, payment_amount, user_id) VALUES (CURRENT_DATE, ?, ?)";

        int rentBalance = jdbcTemplate.queryForObject(
                sql, Integer.class, payment.getUserId());

        int newBalance = rentBalance - payment.getPaymentAmount();

        jdbcTemplate.update(sql2, newBalance, payment.getUserId());
        jdbcTemplate.update(sql3, payment.getPaymentAmount(), payment.getUserId());

        return getAccountBalance(payment.getUserId());
    }


    @Override
    public Payment getPaymentById(int paymentId) {
        String sql = "SELECT * FROM payment WHERE payment_id = ?";
        //  String sql = "SELECT payment_id, payment_date, payment_amount, user_id FROM payment WHERE payment_id = ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, paymentId);
        if (result.next()) {
            return mapRowToPayment(result);
        }
        return null;
    }

    @Override
    public List<Payment> findAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            payments.add(mapRowToPayment(results));
        }
        return payments;
    }

    @Override
    public List<Payment> viewAllRent(LocalDate startDate, LocalDate endDate) {

        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT payment_id, payment_date, payment_amount, user_id "  +
                " FROM payment WHERE payment_date >= ? AND payment_date <= ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, startDate, endDate);
        while (result.next()) {
            Payment payment = mapRowToPayment(result);
            payments.add(payment);
        }

        return payments;
    }
//    private Payment mapRowToPayment(SqlRowSet rs) {
//        Payment payment = new Payment();
//        payment.setUserId(rs.getInt("user_id)"));
//        if (rs.getDate("payment_date") != null) {
//            payment.setPaymentDate(rs.getDate("payment_date").toLocalDate());
//        }
//        payment.setPaymentAmount(rs.getInt("payment_amount"));
//        payment.setPaymentId(rs.getInt("payment_id"));
//        return payment;
//    }

    public boolean updateRentalProperty(Property updatedProperty) {
        String sql = "UPDATE property SET rental_price = ? , address = ?, available = ?, landlord_id = ?, " +
                "due_date= ? WHERE property_id = ?";

        boolean isSuccess = jdbcTemplate.update(sql, updatedProperty.getRentalPrice(), updatedProperty.getAddress(),
                updatedProperty.getAvailable(), updatedProperty.getLandlordId(), updatedProperty.getDueDate(),
                updatedProperty.getId()) == 1;
        return isSuccess;
    }

    // decision to not delete entire payment but set to zero so admin know payment failed keeps serial # going
    @Override
    public boolean deletePayment(Payment payment) {
        String sql = "UPDATE payment SET payment_amount= ? WHERE payment_id = ?";
        return jdbcTemplate.update(sql, 0, payment.getPaymentId()) == 1;

    }


    private Payment mapRowToPayment(SqlRowSet rs) {
        Payment payment = new Payment();
//        payment.setUserId(rs.getInt("user_id)"));
        payment.setUserId(rs.getInt("user_id"));
        if (rs.getDate("payment_date") != null) {
            payment.setPaymentDate(rs.getDate("payment_date").toLocalDate());
        }
        payment.setPaymentAmount(rs.getInt("payment_amount"));
        payment.setPaymentId(rs.getInt("payment_id"));
        return payment;
    }


    @Override
    public AccountBalance getAccountBalance(int userId) {
        AccountBalance accountBalance = new AccountBalance();
        String sql = "SELECT * FROM account_balance WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        if (result.next()) {
            accountBalance.setBalance_id(result.getInt("balance_id"));
            accountBalance.setBalanceAmount(result.getInt("balance_amount"));
            accountBalance.setUserId(userId);
        }
        return accountBalance;
    }

}