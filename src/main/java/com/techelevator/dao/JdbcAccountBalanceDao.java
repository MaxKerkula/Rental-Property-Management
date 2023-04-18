package com.techelevator.dao;

import com.techelevator.model.AccountBalance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountBalanceDao implements AccountBalanceDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountBalanceDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public AccountBalance getAccountBalance(int userId) {
        String sql = "SELECT user_id, balance_amount, balance_id FROM account_balance WHERE user_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        if (rowSet.next()) {
            return mapRowToAccountBalance(rowSet);
        } else {
            return null;
        }
    }

    @Override
    public void updateAccountBalance(AccountBalance account) {
        String sql = "UPDATE account_balance SET balance_amount = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, account.getBalanceAmount(), account.getUserId());
    }

    @Override
    public void addAccountBalance(AccountBalance account) {
        String sql = "INSERT INTO account_balance (user_id, balance_amount, balance_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, account.getUserId(), account.getBalanceAmount(), account.getBalance_id());
    }

    @Override
    public void deleteAccountBalance(int userId) {
        String sql = "DELETE FROM account_balance WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public List<AccountBalance> getAllAccountBalances() {
        String sql = "SELECT user_id, balance_amount, balance_id FROM account_balance";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        List<AccountBalance> accountBalances = new ArrayList<>();
        while (rowSet.next()) {
            accountBalances.add(mapRowToAccountBalance(rowSet));
        }
        return accountBalances;
    }

    private AccountBalance mapRowToAccountBalance(SqlRowSet rs) {
        AccountBalance accountBalance = new AccountBalance();
        int userId = rs.getInt("user_id");
        int balanceAmount = rs.getInt("balance_amount");
        int balanceId = rs.getInt("balance_id");
        return new AccountBalance(userId, balanceAmount, balanceId);
    }
}
