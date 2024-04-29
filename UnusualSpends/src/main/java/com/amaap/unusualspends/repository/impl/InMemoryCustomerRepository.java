package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import jakarta.inject.Inject;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final InMemoryDatabase inMemoryDatabase;

    @Inject
    public InMemoryCustomerRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public Customer add(Customer customer) {
        return inMemoryDatabase.insertIntoCustomerTable(customer);
    }

    @Override
    public Customer findCustomer(int id) {
        return inMemoryDatabase.findCustomer(id);
    }
}
