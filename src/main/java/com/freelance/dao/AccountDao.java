package com.freelance.dao;

import java.util.List;

// import java.util.List;

import com.freelance.models.Account;
import com.freelance.models.Transaction;

public interface AccountDao {

    void createAccount(Account account);

    List<Account> getAllAccountsByCustomerId(Integer customer_id);

    List<Account> getAllAccounts();

    List<Account> getAccountBalanceByUsername(String username);

    List<Account> getAllAccountsByUsername(String username);

    List<Transaction> getAllTransactions();

    void saveAllTransactions(java.sql.Timestamp dateAndTime, Double amount, String transactionType, String username);

    void approveAccountGivenAccountId(Integer id);

    Account getCustomerByUsername(String username);

    void depositIntoCheckingByUsername(String username, Double amount);

    void depositIntoSavingsByUsername(String username, Double amount);

    void transferToSavingsByUsername(String username, Double checking, Double savings);

    void transferToCheckingByUsername(String username, Double checking, Double savings);

    void withdrawalFromCheckingByUsername(String username, Double amount);

    void withdrawalFromSavingsByUsername(String username, Double amount);

    Account getBalanceByUsername(String username);
}
