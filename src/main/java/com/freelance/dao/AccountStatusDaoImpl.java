package com.freelance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.freelance.models.AccountStatus;
import com.freelance.util.ConnectionUtil;

public class AccountStatusDaoImpl implements AccountStatusDao {

    static Logger logger = LogManager.getLogger(AccountStatus.class);

    @Override
    public List<AccountStatus> viewAllAccountStatus() {

        List<AccountStatus> statusList = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from account_status";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                statusList.add(new AccountStatus(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
            }

            conn.close();

        } catch (Exception e) {
            logger.error("SQL exception occured", e);
        }

        return statusList;
    }

    @Override
    public List<AccountStatus> viewAccountStatusByUsername(String username) {

        List<AccountStatus> status = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from account_status where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                status.add(new AccountStatus(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
            }

            conn.close();

        } catch (SQLException e) {
            logger.error("Sql Exception Occured", e);
        }

        return status;
    }

    @Override
    public void approveAccountByUsername(String username) {

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "update account_status set status = 'Approved' where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {
            logger.error("SQL exception occured", e);
        }
    }

    @Override
    public void denyAccountByUsername(String username) {

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "update account_status set status = 'Denied' where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {
            logger.error("SQL exception occured", e);
        }
    }

    @Override
    public AccountStatus getAccountStatusUsername(String username) {

        AccountStatus accountStatusUsername = null;

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "select * from account_status where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                accountStatusUsername = (new AccountStatus(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
            }

            conn.close();

        } catch (SQLException e) {
            logger.error("Sql Exception Occured", e);
        }
        return accountStatusUsername;
    }

    @Override
    public void createStatus(AccountStatus status) {

        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "insert into account_status(username, reason_for_denial) values (?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, status.getUsername());
            ps.setString(2, status.getReasonForDenial());

            ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            logger.error("Sql Exception Occured", e);
        }
    }
}
