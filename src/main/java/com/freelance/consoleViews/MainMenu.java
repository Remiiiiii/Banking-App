package com.freelance.consoleViews;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.freelance.Services.CustomerServices;
import com.freelance.Services.EmployeeServices;
import com.freelance.models.Customer;
import com.freelance.models.Employee;
import com.freelance.util.InputUtil;

public class MainMenu {

    static Logger logger = LogManager.getLogger(MainMenu.class);

    CustomerServices customerService = new CustomerServices();

    EmployeeServices employeeService = new EmployeeServices();

    InputUtil inputUtil = new InputUtil();

    public void view() {

        Scanner scanner = new Scanner(System.in);

        Boolean running = true;

        while (running) {

            System.out.println(
                    "\nFirst National Bank of Deuchland!" +
                            "\n\n1) Login" +
                            "\n2) Register" +
                            "\n0) Logout\n");

            String input = scanner.nextLine();

            switch (input) {

                case "1":

                    String usernameInput = inputUtil.retrieveString("\nUsername: ");

                    String passwordInput = inputUtil.retrieveString("Password: ");

                    Boolean areCredentialsValid = customerService
                            .validateCredentials(new Customer(usernameInput, passwordInput));

                    Boolean areEmployeeCredentialsValid = employeeService
                            .validateCredentials(new Employee(usernameInput, passwordInput));

                    if (areCredentialsValid) {
                        logger.info(usernameInput + "has successfully logged in");
                        Customer customerFromDb = this.customerService.getCustomerByUsername(usernameInput);
                        new CustomerDashboard().view(customerFromDb);

                    } else {

                        System.out.println("Invalid username or password");
                    }

                    if (areEmployeeCredentialsValid) {
                        Employee employeeFromDb = this.employeeService.getEmployeeGivenUsername(usernameInput);
                        new EmployeeDashboard().view(employeeFromDb);

                    } else {

                        System.out.println("Invalid username or password");
                    }

                    break;

                case "2":

                    String usernameInputReg = inputUtil.retrieveString("\nUsername: ");

                    String passwordInputReg = inputUtil.retrieveString("Password: ");

                    String firstNameInputReg = inputUtil.retrieveString("First Name: ");

                    String lastNameInputReg = inputUtil.retrieveString("Last Name: ");

                    Double checkingDeposit = inputUtil.retrieveDouble("Initial Checking deposit amount: $");

                    Double savingsDeposit = inputUtil.retrieveDouble("Initial Savings deposit amount: $");

                    Customer customerToCreate = new Customer(usernameInputReg, passwordInputReg, firstNameInputReg,
                            lastNameInputReg, checkingDeposit, savingsDeposit);

                    Customer customerFromDb = this.customerService.createCustomer(customerToCreate);

                    if (customerFromDb == null) {

                        System.out.println("Username already exists... aboring");
                    } else {
                        System.out.println(
                                "\nRegistration Successful! Your account is under review. \n\nPlease login to check approval or denial status");
                    }
                    break;

                case "0":

                    running = false;

                    break;

                default:

                    System.out.println("Invalid Input");

                    break;
            }
        }

        scanner.close();
    }
}
