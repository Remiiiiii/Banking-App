package com.freelance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.freelance.models.Customer;
import com.freelance.util.ConnectionUtil;

public class CustomerDaoImpl implements CustomerDao {

    static Logger logger = LogManager.getLogger(CustomerDaoImpl.class);

    @Override
    public Customer getCustomerByUsername(String username) {

        Customer customer = null;

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from customer where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customer = (new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7)));
            }

            conn.close();

        } catch (Exception e) {
            logger.error("SQL exception occured", e);
        }

        return customer;

    }

    @Override
    public void createCustomer(Customer customer) {

        try {

            Connection conn = ConnectionUtil.getConnection();

            String sql = "insert into customer(username, password, firstname, lastname, checking_deposit, savings_deposit) values(?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, customer.getUsername());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getFirstname());
            ps.setString(4, customer.getLastname());
            ps.setDouble(5, customer.getCheckingDeposit());
            ps.setDouble(6, customer.getSavingsDeposit());

            ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {

            logger.error("SQL Exception Occured", e);
        }
    }
}
