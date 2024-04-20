package com.amaap.unusualspends.repository.db;

import com.amaap.unusualspends.domain.model.entity.Customer;

public interface InMemoryDatabase {
    Customer insertIntoCustomerTable(Customer customer);

    Customer findCustomer(int id);
}
