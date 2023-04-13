package com.techelevator.model;

import java.util.Objects;

public class AccountBalance {
    private int userId;
    private int balanceAmount;

    public AccountBalance(int userId, int balanceAmount) {
        this.userId = userId;
        this.balanceAmount = balanceAmount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountBalance)) return false;
        AccountBalance that = (AccountBalance) o;
        return getUserId() == that.getUserId() && getBalanceAmount() == that.getBalanceAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getBalanceAmount());
    }

    @Override
    public String toString() {
        return "AccountBalance{" +
                "userId=" + userId +
                ", balanceAmount=" + balanceAmount +
                '}';
    }
}
