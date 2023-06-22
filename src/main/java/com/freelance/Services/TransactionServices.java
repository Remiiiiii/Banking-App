package com.freelance.Services;

import java.util.List;
import com.freelance.dao.TransactionDao;
import com.freelance.dao.TransactionsDaoImpl;
import com.freelance.models.Transaction;

public class TransactionServices {

    TransactionDao transactionDao;

    public TransactionServices() {
        this.transactionDao = new TransactionsDaoImpl();

    }

    public TransactionServices(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public List<Transaction> getAllTransactions() {

        return this.transactionDao.getAllTransactions();
    }

    public void createTransactionHistory(Transaction history) {

        this.transactionDao.createTransactionHistory(history.getDateAndTime(), history.getAmount(),
                history.getTypeOfTransactions(), history.getusername());

    }

}
