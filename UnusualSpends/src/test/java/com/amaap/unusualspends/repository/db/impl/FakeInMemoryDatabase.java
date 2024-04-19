package com.amaap.unusualspends.repository.db.impl;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;

import java.util.ArrayList;
import java.util.List;

public class FakeInMemoryDatabase implements InMemoryDatabase {
    private List<Customer> customers = new ArrayList<>();
    private int customerIdCounter = 1;
    @Override
    public Customer insertIntoCustomerTable(Customer customer) {
        customer.setId(customerIdCounter++);
        customers.add(customer);
        return customer;
    }
}
