package com.freelance.dao;

import java.util.List;

import com.freelance.models.Transaction;

public interface TransactionDao {

    List<Transaction> getAllTransactions();

    void createTransactionHistory(java.sql.Timestamp dateAndTime, Double amount, String transactionType,
            String username);

}
