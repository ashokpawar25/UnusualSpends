package com.amaap.unusualspends.service;

import com.amaap.unusualspends.InMemoryModule;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.service.exception.CustomerNotFoundException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerServiceTest {
    CustomerService customerService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new InMemoryModule());
        customerService = injector.getInstance(CustomerService.class);
    }

    @Test
    void shouldBeAbleToCreateCustomer() throws InvalidCustomerDataException {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id, name, email);

        // act
        Customer actual = customerService.create(name, email);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetCustomerById() throws InvalidCustomerDataException, CustomerNotFoundException {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id, name, email);

        // act
        customerService.create(name, email);
        Customer actual = customerService.find(id);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenCustomerForGivenIdIsNotPresentInDatabase() {
        assertThrows(CustomerNotFoundException.class, () -> customerService.find(1));
    }
}