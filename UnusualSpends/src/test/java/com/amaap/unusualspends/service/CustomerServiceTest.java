package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    CustomerRepository customerRepository = new InMemoryCustomerRepository();
    CustomerService customerService = new CustomerService(customerRepository);
    @Test
    void shouldBeAbleToCreateCustomer()
    {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id,name,email);

        // act
        Customer actual = customerService.create(name,email);

        // assert
        assertEquals(expected,actual);
    }
}