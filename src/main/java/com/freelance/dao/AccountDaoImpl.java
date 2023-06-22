package com.freelance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freelance.models.Account;
import com.freelance.models.Transaction;
import com.freelance.util.ConnectionUtil;

public class AccountDaoImpl implements AccountDao {

    static Logger logger = LogManager.getLogger(AccountDaoImpl.class);

    @Override
    public void createAccount(Account account) {

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "insert into account(username, checking_balance, savings_balance) values(?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, account.getUsername());
            ps.setDouble(2, account.getCheckingBalance());
            ps.setDouble(3, account.getSavingsBalance());
            ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {

            logger.error("SQL exception occured", e);
        }

    }

    @Override
    public List<Account> getAllAccountsByCustomerId(Integer customer_id) {

        List<Account> account = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from account where fk_customer_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, customer_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                account.add(new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4)));
            }

            conn.close();

        } catch (SQLException e) {
            logger.error("SQL exception occured", e);
        }

        return account;
    }

    @Override
    public void approveAccountGivenAccountId(Integer id) {

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "update account set approved = true where fk_customer_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {

            logger.error("SQL exception occured", e);
        }
    }

    @Override
    public List<Account> getAllAccounts() {

        List<Account> accounts = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from account";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                accounts.add(new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4)));
            }

            conn.close();

        } catch (SQLException e) {
            logger.error("SQL exception occured", e);
        }

        return accounts;

    }

    @Override
    public Account getCustomerByUsername(String username) {

        Account account = null;

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from account where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                account = (new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4)));
            }

            conn.close();

        } catch (SQLException e) {
            logger.error("Sql Exception Occured", e);
        }
        return account;

    }

    @Override
    public List<Account> getAccountBalanceByUsername(String username) {

        List<Account> account = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from account where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                account.add(new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4)));
            }

            conn.close();

        } catch (SQLException e) {
            logger.error("Sql Exception Occured", e);
        }
        return account;
    }

    @Override
    public List<Account> getAllAccountsByUsername(String username) {

        List<Account> account = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from account where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                account.add(new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4)));
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void withdrawalFromCheckingByUsername(String username, Double amount) {

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "update account set checking_balance = ? where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setString(2, username);

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {

            logger.error("SQL exception occured", e);
        }

    }

    @Override
    public void withdrawalFromSavingsByUsername(String username, Double amount) {

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "update account set savings_balance = ? where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setString(2, username);

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {

            logger.error("SQL exception occured", e);
        }

    }

    @Override
    public Account getBalanceByUsername(String username) {

        Account account = null;

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from account where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                account = (new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4)));
            }

            conn.close();

        } catch (SQLException e) {
            logger.error("Sql Exception Occured", e);
        }
        return account;

    }

    @Override
    public void depositIntoCheckingByUsername(String username, Double amount) {

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "update account set checking_balance = ? where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setString(2, username);

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {

            logger.error("SQL exception occured", e);
        }

    }

    @Override
    public void depositIntoSavingsByUsername(String username, Double amount) {

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "update account set savings_balance = ? where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setString(2, username);

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {

            logger.error("SQL exception occured", e);
        }
    }

    @Override
    public void transferToSavingsByUsername(String username, Double checking, Double savings) {

        try {

            Connection conn = ConnectionUtil.getConnection();

            String sql = "update account set checking_balance = ?, savings_balance = ? where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, checking);
            ps.setDouble(2, savings);
            ps.setString(3, username);

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {

            logger.error("SQL exception occured", e);
        }
    }

    @Override
    public void transferToCheckingByUsername(String username, Double checking, Double savings) {

        try {

            Connection conn = ConnectionUtil.getConnection();

            String sql = "update account set checking_balance = ?, savings_balance = ? where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, checking);

            ps.setDouble(2, savings);

            ps.setString(3, username);

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {

            logger.error("SQL exception occured", e);
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {

        List<Transaction> transactions = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from transactions";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getInt(1),
                        rs.getTimestamp(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5)));
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;

    }

    @Override
    public void saveAllTransactions(java.sql.Timestamp dateAndTime, Double amount, String transactionType,
            String username) {

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "insert into transactions(date_and_time, transaction_amount, transaction_type, username) values(?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setTimestamp(1, dateAndTime);
            ps.setDouble(2, amount);
            ps.setString(3, transactionType);
            ps.setString(4, username);

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {
            logger.error("SQL exception occured", e);
        }
    }
}
