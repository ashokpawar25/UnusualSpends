package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    CustomerRepository customerRepository = new InMemoryCustomerRepository(inMemoryDatabase);
    CustomerService customerService = new CustomerService(customerRepository);
    @Test
    void shouldBeAbleToCreateCustomer() throws InvalidCustomerDataException {
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

    @Test
    void shouldBeAbleToGetCustomerById() throws InvalidCustomerDataException {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id,name,email);

        // act
        customerService.create(name,email);
        Customer actual = customerService.find(id);

        // assert
        assertEquals(expected,actual);
    }
}