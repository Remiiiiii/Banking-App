package com.freelance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.freelance.models.Transaction;
import com.freelance.util.ConnectionUtil;

public class TransactionsDaoImpl implements TransactionDao {

    static Logger logger = LogManager.getLogger(TransactionsDaoImpl.class);

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

        } catch (Exception e) {
            logger.error("SQL exception occured", e);
        }

        return transactions;
    }

    @Override
    public void createTransactionHistory(Timestamp dateAndTime, Double amount, String transactionType,
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
