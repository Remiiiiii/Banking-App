package com.freelance.models;

public class Account {

    private Integer accountId;
    private String username;
    private Double checkingBalance;
    private Double savingsBalance;

    public Account() {
    }

    public Account(String username) {
        this.username = username;
    }

    public Account(String username, Double checkingBalance) {
        this.username = username;
        this.checkingBalance = checkingBalance;
    }

    public Account(String username, Double checkingBalance, Double savingsBalance) {
        this.username = username;
        this.checkingBalance = checkingBalance;
        this.savingsBalance = savingsBalance;
    }

    public Account(Integer accountId, String username, Double checkingBalance, Double savingsBalance) {
        this.accountId = accountId;
        this.username = username;
        this.checkingBalance = checkingBalance;
        this.savingsBalance = savingsBalance;
    }

    public Integer getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getCheckingBalance() {
        return this.checkingBalance;
    }

    public void setCheckingBalance(Double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public Double getSavingsBalance() {
        return this.savingsBalance;
    }

    public void setSavingsBalance(Double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    @Override
    public String toString() {
        return "{" +
                " accountId='" + getAccountId() + "'" +
                ", username='" + getUsername() + "'" +
                ", checkingBalance='" + getCheckingBalance() + "'" +
                ", savingsBalance='" + getSavingsBalance() + "'" +
                "}";
    }

}
