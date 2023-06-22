package com.freelance.consoleViews;

import java.util.List;

import com.freelance.Services.AccountServices;
import com.freelance.Services.AccountStatusServices;
import com.freelance.Services.EmployeeServices;
import com.freelance.Services.TransactionServices;
import com.freelance.models.Account;
import com.freelance.models.AccountStatus;
import com.freelance.models.Customer;
import com.freelance.models.Employee;
import com.freelance.models.Transaction;
import com.freelance.util.InputUtil;

public class EmployeeDashboard {

    Account account = new Account();

    AccountServices accountServices = new AccountServices();

    AccountStatusServices statusServices = new AccountStatusServices();

    TransactionServices transactionServices = new TransactionServices();

    Customer customerFromDb = new Customer();

    InputUtil inputUtil = new InputUtil();

    EmployeeServices employeeService = new EmployeeServices();

    public void view(Employee employeeFromDb) {

        Boolean running = true;

        System.out.println("\nWelcome " + employeeFromDb.getFirstname() + "!");

        while (running) {

            System.out.println(
                    "\n1) View all customer accounts" +
                            "\n2) View customer account status" +
                            "\n3) Approve customer account" +
                            "\n4) Deny customer account" +
                            "\n5) Create a customer account" +
                            "\n6) View transaction log" +
                            "\n7) Create customer status log" +
                            "\n0) Return to Main Menu");

            String input = inputUtil.retrieveString("\nChoose an option: ");

            switch (input) {
                case "1":
                    System.out.println("\n");
                    printAccounts();
                    break;

                case "2":
                    System.out.println("\n");
                    printStatusAccounts();

                    break;

                case "3":

                    String usernameInputApprove = inputUtil.retrieveString("Username: ");

                    Boolean isUsernameValidApprove = statusServices.validateCredentials(usernameInputApprove);

                    if (isUsernameValidApprove) {

                        this.statusServices.approveAccountByUsername(usernameInputApprove);

                        System.out.println("Account approved");

                    } else {
                        System.out.println("Username doesn't exist or is invalid");
                    }

                    break;

                case "4":

                    String usernameInputDeny = inputUtil.retrieveString("Username: ");

                    Boolean isUsernameValidDeny = statusServices.validateCredentials(usernameInputDeny);

                    if (isUsernameValidDeny) {

                        this.statusServices.denyAccountByUsername(usernameInputDeny);

                        System.out.println("Account denied");

                    } else {
                        System.out.println("Username doesn't exist or is invalid");
                    }

                    break;

                case "5":

                    String usernameInput = inputUtil.retrieveString("\nUsername: ");
                    Double checkingDeposit = inputUtil.retrieveDouble("Checking deposit amount: $");
                    Double savingsDeposit = inputUtil.retrieveDouble("Saving deposit amount: $");

                    Account accountToCreate = new Account(usernameInput, checkingDeposit, savingsDeposit);

                    Account accountFromDb = this.accountServices.createAccount(accountToCreate);

                    if (accountFromDb == null) {

                        System.out.println("An account with similar info already exists... aborting!");

                    } else {

                        System.out.println("\nAccount Created");
                    }

                    break;

                case "6":

                    printTransactionLog();

                    break;

                case "7":

                    String statusUsernameInput = inputUtil.retrieveString("Username: ");

                    String statusReasonForDenial = inputUtil.retrieveString("Reason for denial: ");

                    this.statusServices.createStatus(new AccountStatus(statusUsernameInput, statusReasonForDenial));

                    break;

                case "0":

                    running = false;

                    break;

                default:

                    System.out.println("Invalid input");

                    break;
            }

        }
    }

    private void printAccounts() {

        List<Account> accounts = this.accountServices.getAllAccounts();

        for (Account account : accounts) {

            System.out.println(account);
        }

    }

    private void printStatusAccounts() {

        List<AccountStatus> accounts = this.statusServices.viewAllAccountStatus();

        for (AccountStatus account : accounts) {
            System.out.println("\nStatus id: " + account.getStatusId() + "\nUsername: " + account.getUsername()
                    + "\nStatus: " + account.getStatus() + "\nReason for denial: " + account.getReasonForDenial());
        }

    }

    private void printTransactionLog() {

        List<Transaction> transactions = this.transactionServices.getAllTransactions();

        for (Transaction transaction : transactions) {
            System.out
                    .println("\nDate and time: " + transaction.getDateAndTime() + "\nAmount: " + transaction.getAmount()
                            + "\nType of transaction: " + transaction.getTypeOfTransactions() + "\nCustomer: "
                            + transaction.getusername());
        }

    }

}
