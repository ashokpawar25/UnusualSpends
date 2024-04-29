package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.service.exception.CustomerNotFoundException;
import jakarta.inject.Inject;

public class CustomerService {

    private final CustomerRepository customerRepository;

    @Inject
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(String name, String email) throws InvalidCustomerDataException {
        Customer customer = Customer.create(1, name, email);
        return customerRepository.add(customer);
    }

    public Customer find(int id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findCustomer(id);
        if (customer == null) throw new CustomerNotFoundException("Customer with id:" + id + " not found");
        return customer;
    }
}
