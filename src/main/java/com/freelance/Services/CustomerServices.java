package com.freelance.Services;

import com.freelance.dao.CustomerDao;
import com.freelance.dao.CustomerDaoImpl;
import com.freelance.models.Customer;

public class CustomerServices {

    CustomerDao customerDao;

    public CustomerServices(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public CustomerServices() {
        this.customerDao = new CustomerDaoImpl();
    }

    public Boolean validateCredentials(Customer credentials) {

        Customer customerFromDb = customerDao.getCustomerByUsername(credentials.getUsername());

        if (customerFromDb == null) {
            return false;
        }

        if (customerFromDb.getPassword().equals(credentials.getPassword())) {
            return true;
        }

        return false;
    }

    public Customer getCustomerByUsername(String username) {

        return customerDao.getCustomerByUsername(username);
    }

    public Customer createCustomer(Customer customerToCreate) {

        Customer customerFromDb = customerDao.getCustomerByUsername(customerToCreate.getUsername());

        if (customerFromDb == null) {

            customerDao.createCustomer(customerToCreate);

            return customerToCreate;

        }

        return null;
    }
}
