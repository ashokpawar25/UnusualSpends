package com.amaap.unusualspends.repository;

import com.amaap.unusualspends.domain.model.entity.Customer;

public interface CustomerRepository {
    Customer add(Customer customer);

    Customer findCustomer(int id);
}
