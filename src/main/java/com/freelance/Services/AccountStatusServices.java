package com.freelance.Services;

import java.util.List;

import com.freelance.dao.AccountStatusDao;
import com.freelance.dao.AccountStatusDaoImpl;
import com.freelance.models.AccountStatus;

public class AccountStatusServices {

    AccountStatusDao accountStatusDao;

    public AccountStatusServices() {

        this.accountStatusDao = new AccountStatusDaoImpl();
    }

    public AccountStatusServices(AccountStatusDao accountStatusDao) {

        this.accountStatusDao = accountStatusDao;
    }

    CustomerServices customerServices = new CustomerServices();

    public boolean validateCredentials(String usernameCredentials) {

        AccountStatus usernameFromDb = accountStatusDao.getAccountStatusUsername(usernameCredentials);

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

    public boolean validateStatus() {

        AccountStatus statusFromDb = new AccountStatus();

        try {

            if (statusFromDb.getStatus().equals("Approved")) {

                return true;
            }

        } catch (Exception e) {
            System.out.println("\nYou will need to be approved first before creating an account");
        }

        return false;

    }

    public List<AccountStatus> viewAccountStatusByUsername(String username) {

        return this.accountStatusDao.viewAccountStatusByUsername(username);
    }

    public List<AccountStatus> viewAllAccountStatus() {

        return this.accountStatusDao.viewAllAccountStatus();
    }

    public void approveAccountByUsername(String username) {

        this.accountStatusDao.approveAccountByUsername(username);
    }

    public void denyAccountByUsername(String username) {

        this.accountStatusDao.denyAccountByUsername(username);
    }

    public void createStatus(AccountStatus status) {

        this.accountStatusDao.createStatus(status);
    }

}
