package com.freelance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.freelance.models.Customer;
import com.freelance.models.Employee;
import com.freelance.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

    static Logger logger = LogManager.getLogger(EmployeeDaoImpl.class);

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
                        rs.getString(5)));
                // rs.getDouble(6),
                // rs.getDouble(7)));
            }

            conn.close();

        } catch (Exception e) {
            logger.error("SQL exception occured", e);
        }

        return customer;
    }

    @Override
    public void createCustomerAccount(Customer account) {

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "insert into account(username, checking_balance, savings_balance) values(?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, account.getUsername());
            // ps.setDouble(2, account.getCheckingDeposit());
            // ps.setDouble(3, account.getSavingsDeposit());
            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {
            logger.error("SQL exception occured", e);
        }
    }

    @Override
    public Employee getEmployeeGivenUsername(String username) {

        Employee employee = null;

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from employee where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                employee = (new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }

            conn.close();

        } catch (Exception e) {
            logger.error("SQL exception occured", e);
        }

        return employee;
    }

}
