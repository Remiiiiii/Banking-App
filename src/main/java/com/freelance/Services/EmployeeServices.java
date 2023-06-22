package com.freelance.Services;

import com.freelance.dao.EmployeeDao;
import com.freelance.dao.EmployeeDaoImpl;
import com.freelance.models.Customer;
import com.freelance.models.Employee;

public class EmployeeServices {

    EmployeeDao employeeDao;

    public EmployeeServices(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public EmployeeServices() {
        this.employeeDao = new EmployeeDaoImpl();
    }

    public Boolean validateCredentials(Employee credentials) {

        Employee employeeFromDb = employeeDao.getEmployeeGivenUsername(credentials.getUsername());

        if (employeeFromDb == null) {
            return false;
        }

        if (employeeFromDb.getPassword().equals(credentials.getPassword())) {
            return true;
        }

        return false;
    }

    public Employee getEmployeeGivenUsername(String username) {

        return employeeDao.getEmployeeGivenUsername(username);
    }

    public void createCustomerAccount(Customer customerAccount) {
        this.employeeDao.createCustomerAccount(customerAccount);
    }
}
