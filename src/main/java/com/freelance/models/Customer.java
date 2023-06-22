package com.freelance.models;

public class Customer {

    private Integer id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Double checkingDeposit;
    private Double savingsDeposit;

    public Customer() {
    }

    public Customer(String username) {
        this.username = username;
    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Customer(Integer id, String username, String password, String firstname, String lastname,
            Double checkingDeposit, Double savingsDeposit) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.checkingDeposit = checkingDeposit;
        this.savingsDeposit = savingsDeposit;

    }

    public Customer(String username, String password, String firstname, String lastname,
            Double checkingDeposit,
            Double savingsDeposit) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.checkingDeposit = checkingDeposit;
        this.savingsDeposit = savingsDeposit;

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Double getCheckingDeposit() {
        return this.checkingDeposit;
    }

    public void setCheckingDeposit(Double checkingDeposit) {
        this.checkingDeposit = checkingDeposit;
    }

    public Double getSavingsDeposit() {
        return this.savingsDeposit;
    }

    public void setSavingsDeposit(Double savingsDeposit) {
        this.savingsDeposit = savingsDeposit;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", username='" + getUsername() + "'" +
                ", password='" + getPassword() + "'" +
                ", firstname='" + getFirstname() + "'" +
                ", lastname='" + getLastname() + "'" +
                ", initialDeposit='" + getCheckingDeposit() + "'" +
                ", accountType='" + getSavingsDeposit() + "'" +
                "}";
    }

}
