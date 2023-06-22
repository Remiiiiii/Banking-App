package com.freelance.models;

public class Transaction {

    private Integer id;
    private java.sql.Timestamp dateAndTime;
    private Double amount;
    private String typeOfTransactions;
    private String username;

    public Transaction() {
    }

    public Transaction(Integer id, java.sql.Timestamp dateAndTime, Double amount, String typeOfTransactions,
            String username) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.amount = amount;
        this.typeOfTransactions = typeOfTransactions;
        this.username = username;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.sql.Timestamp getDateAndTime() {
        return this.dateAndTime;
    }

    public void setDateAndTime(java.sql.Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTypeOfTransactions() {
        return this.typeOfTransactions;
    }

    public void setTypeOfTransactions(String typeOfTransactions) {
        this.typeOfTransactions = typeOfTransactions;
    }

    public String getusername() {
        return this.username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", dateAndTime='" + getDateAndTime() + "'" +
                ", amount='" + getAmount() + "'" +
                ", typeOfTransactions='" + getTypeOfTransactions() + "'" +
                ", username='" + getusername() + "'" +
                "}";
    }

}
