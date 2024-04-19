package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.repository.CustomerRepository;

public class CustomerService {
    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(String name, String email) {
        Customer customer = Customer.create(0,name,email);
        return customerRepository.add(customer);
    }
}
