package com.freelance.consoleViews;

import java.util.List;

import com.freelance.Services.AccountServices;
import com.freelance.Services.AccountStatusServices;
import com.freelance.Services.CustomerServices;
import com.freelance.models.Account;
import com.freelance.models.AccountStatus;
import com.freelance.models.Customer;
import com.freelance.util.InputUtil;

public class CustomerDashboard {

    public String usernameInput;

    AccountServices accountServices = new AccountServices();

    Account account = new Account();

    AccountStatusServices accountStatusServices = new AccountStatusServices();

    CustomerServices customerServices = new CustomerServices();

    Customer customer = new Customer();

    InputUtil inputUtil = new InputUtil();

    public void view(Customer customerFromDb) {

        System.out.println("\nWelcome " + customerFromDb.getFirstname() + "!");

        Boolean running = true;

        while (running) {

            System.out.println(
                    "\n1) Check status" +
                            "\n2) View Account Balances" +
                            "\n3) Deposit into Checking" +
                            "\n4) Deposit into Savings" +
                            "\n5) Withdrawal from Checking" +
                            "\n6) Withdrawal from Savings" +
                            "\n7) Transfer  funds to savings account" +
                            "\n8) Transfer  funds to checking account" +
                            "\n0) Return to Main Menu");

            String input = inputUtil.retrieveString("\nChoose an option: ");

            switch (input) {
                case "1":

                    usernameInput = inputUtil.retrieveString("\nUsername: ");

                    Boolean isUsernameValid = accountStatusServices.validateCredentials(usernameInput);

                    if (isUsernameValid) {

                        printAccountStatus(usernameInput);
                    }

                    break;

                case "2":

                    String usernameForBalanceInput = inputUtil.retrieveString("\nUsername: ");

                    Boolean isUsernameForBalanceValid = accountServices.validateCredentials(usernameForBalanceInput);

                    if (isUsernameForBalanceValid) {

                        printAccountBalances(usernameForBalanceInput);

                    }

                    break;

                case "3":

                    String usernameFromDepositAccountInput = inputUtil.retrieveString("Username: ");

                    Account usernameOfAccountForDeposit = accountServices
                            .getBalanceByUsername(usernameFromDepositAccountInput);

                    Double amount = inputUtil.retrieveDouble("Amount: $");

                    Boolean isUsernameFromAccountValid = accountServices
                            .validateCredentials(usernameFromDepositAccountInput);

                    if (isUsernameFromAccountValid) {

                        System.out.println("\nPrevious account balance: ");

                        printBalance(usernameFromDepositAccountInput);

                        this.accountServices.depositIntoCheckingByUsername(usernameOfAccountForDeposit, amount);

                        System.out.println("\n----------------------------------");
                        System.out.println("\nNew account balance: ");

                        printBalance(usernameFromDepositAccountInput);

                    } else {

                        System.out.println("Ivalid entry");
                    }

                    break;

                case "4":

                    String usernameFromSavingsAccountForDepositInput = inputUtil.retrieveString("Username: ");

                    Account usernameOfSavingsForDeposit = accountServices
                            .getBalanceByUsername(usernameFromSavingsAccountForDepositInput);

                    Double amountToDeposit = inputUtil.retrieveDouble("Amount: $");

                    Boolean isUsernameFromSavingsAccountValid = accountServices
                            .validateCredentials(usernameFromSavingsAccountForDepositInput);

                    if (isUsernameFromSavingsAccountValid) {

                        printSavingsBalance(usernameFromSavingsAccountForDepositInput);

                        this.accountServices.depositIntoSavingsByUsername(usernameOfSavingsForDeposit, amountToDeposit);

                        System.out.println("\n----------------------------------");
                        System.out.println("\nNew account balance: ");

                        printSavingsBalance(usernameFromSavingsAccountForDepositInput);

                    } else {

                        System.out.println("Ivalid entry");
                    }

                    break;

                case "5":

                    String usernameFromCheckingAccountInput = inputUtil.retrieveString("Username: ");

                    Account usernameOfAccountForWithdrawal = accountServices
                            .getBalanceByUsername(usernameFromCheckingAccountInput);

                    Double amountToDepositIntoChecking = inputUtil.retrieveDouble("Amount: $");

                    Boolean isUsernameFromAccountValidForDeposit = accountServices
                            .validateCredentials(usernameFromCheckingAccountInput);

                    if (isUsernameFromAccountValidForDeposit) {

                        System.out.println("\nPrevious account balance: ");

                        printBalance(usernameFromCheckingAccountInput);

                        this.accountServices.withdrawalFromCheckingByUsername(usernameOfAccountForWithdrawal,
                                amountToDepositIntoChecking);

                        System.out.println("\n----------------------------------");
                        System.out.println("\nNew account balance: ");

                        printBalance(usernameFromCheckingAccountInput);

                    } else {

                        System.out.println("Ivalid entry");
                    }

                    break;

                case "6":

                    String usernameFromSavingsAccountInput = inputUtil.retrieveString("Username: ");

                    Account usernameOfSavingsAccountForWithdrawal = accountServices
                            .getBalanceByUsername(usernameFromSavingsAccountInput);

                    Double amountToDepositIntoSavings = inputUtil.retrieveDouble("Amount: $");

                    Boolean isUsernameFromAccountValidForWithdrawal = accountServices
                            .validateCredentials(usernameFromSavingsAccountInput);

                    if (isUsernameFromAccountValidForWithdrawal) {

                        System.out.println("\nPrevious account balance: ");

                        printSavingsBalance(usernameFromSavingsAccountInput);

                        this.accountServices.withdrawalFromSavingsByUsername(usernameOfSavingsAccountForWithdrawal,
                                amountToDepositIntoSavings);

                        System.out.println("\n----------------------------------");
                        System.out.println("\nNew account balance: ");

                        printSavingsBalance(usernameFromSavingsAccountInput);

                    } else {

                        System.out.println("Ivalid entry");
                    }

                    break;

                case "7":

                    String usernameForTransferInput = inputUtil.retrieveString("Username: ");

                    Double withdrawalAmount = inputUtil.retrieveDouble("Amount: $");

                    Account usernameForTransfer = accountServices
                            .getBalanceByUsername(usernameForTransferInput);

                    Boolean isUsernameValidForTransfer = accountServices
                            .validateCredentials(usernameForTransferInput);

                    if (isUsernameValidForTransfer) {

                        System.out.println("\nPrevious account balance: ");
                        printAccountBalances(usernameForTransferInput);

                        this.accountServices.transferToSavingsByUsername(usernameForTransfer, withdrawalAmount);

                        System.out.println("\n----------------------------------");
                        System.out.println("\nNew account balance: ");
                        printAccountBalances(usernameForTransferInput);

                    } else {

                        System.out.println("Ivalid entry");
                    }

                    break;

                case "8":

                    String usernameForTransferToCheckingInput = inputUtil.retrieveString("Username: ");

                    Double transferAmount = inputUtil.retrieveDouble("Amount: $");

                    Account usernameForTransferToChecking = accountServices
                            .getBalanceByUsername(usernameForTransferToCheckingInput);

                    Boolean isUsernameValidForTransferToChecking = accountServices
                            .validateCredentials(usernameForTransferToCheckingInput);

                    if (isUsernameValidForTransferToChecking) {

                        System.out.println("\nPrevious account balance: ");

                        printAccountBalances(usernameForTransferToCheckingInput);

                        this.accountServices.transferToCheckingByUsername(usernameForTransferToChecking,
                                transferAmount);

                        System.out.println("\n----------------------------------");

                        System.out.println("\nNew account balance: ");

                        printAccountBalances(usernameForTransferToCheckingInput);

                    } else {

                        System.out.println("Ivalid entry");
                    }

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

    private void printAccountStatus(String username) {

        List<AccountStatus> status = this.accountStatusServices.viewAccountStatusByUsername(username);

        for (AccountStatus accountStatuses : status) {

            System.out.println("\nAccount status: " + accountStatuses.getStatus() + "\nReason for denial: "
                    + accountStatuses.getReasonForDenial());
        }
    }

    private void printAccountBalances(String username) {

        List<Account> balance = this.accountServices.getAccountBalanceByUsername(username);

        for (Account account : balance) {
            System.out
                    .println("\nChecking account balance: $" + account.getCheckingBalance()
                            + "\nSavings account balance: " + account.getSavingsBalance());
        }
    }

    private void printBalance(String username) {

        List<Account> balance = this.accountServices.getAccountBalanceByUsername(username);

        for (Account account : balance) {
            System.out.println("\nAccount balance: $" + account.getCheckingBalance());
        }
    }

    private void printSavingsBalance(String username) {

        List<Account> balance = this.accountServices.getAccountBalanceByUsername(username);

        for (Account account : balance) {
            System.out.println("\nAccount balance: $" + account.getSavingsBalance());
        }
    }
}
