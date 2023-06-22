package com.freelance.Services;

import java.util.List;
import com.freelance.dao.AccountDao;
import com.freelance.dao.AccountDaoImpl;
import com.freelance.dao.CustomerDao;
import com.freelance.dao.CustomerDaoImpl;
import com.freelance.dao.TransactionDao;
import com.freelance.dao.TransactionsDaoImpl;
import com.freelance.models.Account;
import com.freelance.models.Transaction;

public class AccountServices {

    AccountDao accountDao;
    AccountDao accountDaoImpl;
    CustomerDao customerDao;
    TransactionDao transactionDao;

    public AccountServices() {
        this.accountDao = new AccountDaoImpl();
        this.accountDaoImpl = new AccountDaoImpl();
        this.customerDao = new CustomerDaoImpl();
        this.transactionDao = new TransactionsDaoImpl();

    }

    public AccountServices(AccountDao accountDao) {
        this.accountDao = accountDao;
        this.accountDaoImpl = new AccountDaoImpl();
        this.customerDao = new CustomerDaoImpl();
        this.transactionDao = new TransactionsDaoImpl();
    }

    public boolean validateCredentials(String usernameCredentials) {

        Account usernameFromDb = accountDao.getCustomerByUsername(usernameCredentials);

        try {

            if (usernameFromDb.getUsername().equals(usernameCredentials)) {

                return true;
            }

        } catch (Exception e) {
            System.out.println("\nInvalid username");
            System.out.println("Your account may be pending approval therefore has not yet been created");

        }

        return false;

    }

    public Account createAccount(Account accountToCreate) {

        Account accountFromDb = accountDao.getCustomerByUsername(accountToCreate.getUsername());

        if (accountFromDb == null) {

            accountDao.createAccount(accountToCreate);

            return accountToCreate;
        }

        return null;
    }

    public List<Account> getAllAccounts() {

        return this.accountDao.getAllAccounts();

    }

    public void approveAccountGivenAccountId(Integer id) {

        this.accountDao.approveAccountGivenAccountId(id);
    }

    public List<Account> getAllAccountsByUsername(String username) {

        return this.accountDao.getAllAccountsByUsername(username);
    }

    public List<Account> getAccountBalanceByUsername(String username) {

        return this.accountDao.getAccountBalanceByUsername(username);
    }

    public Boolean withdrawalFromCheckingByUsername(Account account, Double amount) {

        if (amount <= 0) {
            System.out.println("Amount must be greater than $00.00");
            return false;
        }

        if ((amount < 0) || account.getCheckingBalance() - amount < 0) {
            return false;
        }

        String type = "Withdrawal from checking";

        java.util.Date currentDate = new java.util.Date();
        long currentTimeMillis = currentDate.getTime();
        java.sql.Timestamp dateAndTime = new java.sql.Timestamp(currentTimeMillis);

        accountDao.saveAllTransactions(dateAndTime, amount, type, account.getUsername());

        amount = account.getCheckingBalance() - amount;

        accountDao.withdrawalFromCheckingByUsername(account.getUsername(), amount);

        return true;

    }

    public Boolean withdrawalFromSavingsByUsername(Account account, Double amount) {

        if (amount <= 0 || (account.getSavingsBalance() - amount) < 0) {
            System.out.println("Insufficient funds");
            return false;
        } else {

            String type = "Withdrawal from savings";

            java.util.Date currentDate = new java.util.Date();
            long currentTimeMillis = currentDate.getTime();
            java.sql.Timestamp dateAndTime = new java.sql.Timestamp(currentTimeMillis);

            accountDao.saveAllTransactions(dateAndTime, amount, type, account.getUsername());

            amount = account.getSavingsBalance() - amount;

            accountDao.withdrawalFromSavingsByUsername(account.getUsername(), amount);
        }

        return false;
    }

    public Account getBalanceByUsername(String username) {

        return this.accountDao.getBalanceByUsername(username);
    }

    public Boolean depositIntoCheckingByUsername(Account account, Double amount) {

        if (amount <= 0) {

            System.out.println("Deposit amount must be greater than $0.00");

            return false;
        }

        String type = "Deposit into checking";

        java.util.Date currentDate = new java.util.Date();
        long currentTimeMillis = currentDate.getTime();
        java.sql.Timestamp dateAndTime = new java.sql.Timestamp(currentTimeMillis);

        accountDao.saveAllTransactions(dateAndTime, amount, type, account.getUsername());

        amount = account.getCheckingBalance() + amount;

        accountDao.depositIntoCheckingByUsername(account.getUsername(), amount);

        return false;
    }

    public Boolean depositIntoSavingsByUsername(Account account, Double amount) {

        if (amount <= 0) {

            System.out.println("Deposit amount must be greater than $0.00");

            return false;
        }

        String type = "Deposit into savings";

        java.util.Date currentDate = new java.util.Date();
        long currentTimeMillis = currentDate.getTime();
        java.sql.Timestamp dateAndTime = new java.sql.Timestamp(currentTimeMillis);

        accountDao.saveAllTransactions(dateAndTime, amount, type, account.getUsername());

        amount = account.getSavingsBalance() + amount;

        accountDao.depositIntoSavingsByUsername(account.getUsername(), amount);
        ;

        return false;
    }

    public Boolean transferToSavingsByUsername(Account account, Double amountToSavings) {

        if (amountToSavings <= 0) {

            System.out.println("The amount to be transferred must be greater than $00.00");
            return false;
        }

        else if (account.getCheckingBalance() <= 0) {

            System.out.println("Insufficient funds for transfer");

        } else {

            String type = "Transfer to savings";

            java.util.Date currentDate = new java.util.Date();
            long currentTimeMillis = currentDate.getTime();
            java.sql.Timestamp dateAndTime = new java.sql.Timestamp(currentTimeMillis);

            accountDao.saveAllTransactions(dateAndTime, amountToSavings, type, account.getUsername());

            Double amountFromChecking = account.getCheckingBalance() - amountToSavings;

            amountToSavings += account.getSavingsBalance();

            accountDao.transferToSavingsByUsername(account.getUsername(), amountFromChecking, amountToSavings);

            return true;

        }

        return false;
    }

    public Boolean transferToCheckingByUsername(Account account, Double amountToChecking) {

        if (amountToChecking <= 0) {

            System.out.println("The amount to be transferred must be greater than $00.00");
            return false;
        }

        else if (account.getSavingsBalance() <= 0) {

            System.out.println("Insufficient funds for transfer");

        } else {

            String type = "Transfer to checking";

            java.util.Date currentDate = new java.util.Date();
            long currentTimeMillis = currentDate.getTime();
            java.sql.Timestamp dateAndTime = new java.sql.Timestamp(currentTimeMillis);

            accountDao.saveAllTransactions(dateAndTime, amountToChecking, type, account.getUsername());

            Double amountFromSavings = account.getSavingsBalance() - amountToChecking;

            amountToChecking += account.getCheckingBalance();

            accountDao.transferToCheckingByUsername(account.getUsername(), amountFromSavings, amountToChecking);

            Transaction now = new Transaction();

            transactionDao.createTransactionHistory(now.getDateAndTime(), amountToChecking, type,
                    account.getUsername());

            return true;

        }

        return false;
    }
}
