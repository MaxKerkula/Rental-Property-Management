package com.techelevator.dao;

import com.techelevator.model.AccountBalance;

import java.util.List;

public interface AccountBalanceDao {
    AccountBalance getAccountBalance(int userId);

    void updateAccountBalance(AccountBalance account);

    void addAccountBalance(AccountBalance account);

    void deleteAccountBalance(int userId);

    List<AccountBalance> getAllAccountBalances();
}