package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import com.amaap.unusualspends.service.CustomerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    CustomerRepository customerRepository = new InMemoryCustomerRepository(inMemoryDatabase);
    CustomerService customerService = new CustomerService(customerRepository);
    CustomerController customerController = new CustomerController(customerService);

    @Test
    void shouldBeAbleToCreateCustomer() throws InvalidCustomerDataException {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Response expected = new Response(HttpStatus.OK,"Customer created successfully");

        // act
        Response actual = customerController.create(name,email);

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
        customerController.create(name,email);
        Customer actual = customerController.find(id);

        // assert
        assertEquals(expected,actual);
    }
}
