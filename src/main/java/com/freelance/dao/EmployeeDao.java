package com.freelance.dao;

import com.freelance.models.Customer;
import com.freelance.models.Employee;

public interface EmployeeDao {

    Customer getCustomerByUsername(String username);

    void createCustomerAccount(Customer customerAccount);

    Employee getEmployeeGivenUsername(String username);
}
