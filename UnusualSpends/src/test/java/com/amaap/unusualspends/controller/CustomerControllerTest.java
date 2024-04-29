package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.InMemoryModule;
import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.service.exception.CustomerNotFoundException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerControllerTest {
    CustomerController customerController;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new InMemoryModule());
        customerController = injector.getInstance(CustomerController.class);
    }

    @Test
    void shouldBeAbleToGetOkResponseWhenCustomerCreatedSuccessfully() {
        // arrange
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Response expected = new Response(HttpStatus.OK, "Customer created successfully");

        // act
        Response actual = customerController.create(name, email);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetBadRequestAsResponseWhenPassedDataIsInvalid() {
        // arrange
        String name = "Ashok Pa";
        String email = "ashokpawar@gmail.com";
        Response expected = new Response(HttpStatus.BAD_REQUEST, "Invalid customer name " + name);

        // act
        Response actual = customerController.create(name, email);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetCustomerById() throws CustomerNotFoundException {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id, name, email);

        // act
        customerController.create(name, email);
        Customer actual = customerController.find(id);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenCustomerForGivenIdIsNotPresentInDatabase() {
        assertThrows(CustomerNotFoundException.class, () -> customerController.find(1));
    }
}
