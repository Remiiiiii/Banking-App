package com.freelance.dao;

import com.freelance.models.Customer;

public interface CustomerDao {

    Customer getCustomerByUsername(String username);

    void createCustomer(Customer customer);
}
