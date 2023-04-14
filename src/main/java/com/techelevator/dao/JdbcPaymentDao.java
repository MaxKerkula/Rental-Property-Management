package com.techelevator.dao;

import com.techelevator.model.Payment;
import com.techelevator.model.Users;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDate;
import java.util.List;

public class JdbcPaymentDao implements PaymentDao {

    private  JdbcTemplate jdbcTemplate;

    public JdbcPaymentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer viewRentBill(int userId) {

        Integer rentToBePaid;
            rentToBePaid = jdbcTemplate.queryForObject("SELECT balance_amount FROM account_balance WHERE user_id = ?", int.class, userId);
        return rentToBePaid;
    }

    @Override
    public int payRent(int rentPayment, int userId) {
        String sql = "SELECT balance_amount FROM account_balance WHERE user_id = ?";
        String sql2 = "UPDATE account_balances SET balance_amount = ? WHERE user_id = ?";
        String sql3 = "INSERT INTO payment (payment_date, payment_amount, user_id) VALUES (CURRENT_DATE, ?, ?)";
        int rentBalance = jdbcTemplate.queryForObject(
                sql, int.class, userId);

        int newBalance = rentBalance - rentPayment;

        jdbcTemplate.update(sql2, newBalance, userId);
        jdbcTemplate.update(sql3, rentPayment, userId);

        return newBalance;
    }

    @Override
    public List<Payment> viewAllRent(LocalDate startDate, LocalDate endDate) {
        List<Payment> payments = jdbcTemplate.query(  // need
                "SELECT * FROM payment WHERE payment_date >= ? AND payment_date <= ?",
                new Object[]{startDate, endDate},
                (rs, rowNum) -> mapRowToPayment((SqlRowSet) rs));

        return payments;
    }

    private Payment mapRowToPayment(SqlRowSet rs) {
        Payment payment = new Payment();
        payment.setUserId(rs.getInt("user_id)"));
        if(rs.getDate("payment_date") != null){
            payment.setPaymentDate(rs.getDate("payment_date").toLocalDate());
        }
        payment.setPaymentAmount(rs.getInt("payment_amount"));
        payment.setPaymentId(rs.getInt("payment_id"));
        return payment;
    }

}