package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final InMemoryDatabase inMemoryDatabase;

    public InMemoryCustomerRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public Customer add(Customer customer) {
        return inMemoryDatabase.insertIntoCustomerTable(customer);
    }
}
