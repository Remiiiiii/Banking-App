package com.freelance.dao;

import java.util.List;

import com.freelance.models.AccountStatus;

public interface AccountStatusDao {

    List<AccountStatus> viewAllAccountStatus();

    List<AccountStatus> viewAccountStatusByUsername(String username);

    void approveAccountByUsername(String username);

    void denyAccountByUsername(String username);

    AccountStatus getAccountStatusUsername(String username);

    void createStatus(AccountStatus status);

}
