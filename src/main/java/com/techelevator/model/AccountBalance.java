package com.techelevator.model;

public class AccountBalance {
    private int userId;

    private int balanceAmount;
    private int balance_id;

    public AccountBalance() {

    }

    public AccountBalance(int userId, int balanceAmount, int balance_id) {
        this.userId = userId;
        this.balanceAmount = balanceAmount;
        this.balance_id = balance_id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(int balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public int getBalance_id() {
        return balance_id;
    }

    public void setBalance_id(int balance_id) {
        this.balance_id = balance_id;
    }


}