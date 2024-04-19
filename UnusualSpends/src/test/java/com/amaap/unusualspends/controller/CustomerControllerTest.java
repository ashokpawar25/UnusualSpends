package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.domain.model.entity.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest {
    CustomerController customerController = new CustomerController();

    @Test
    void shouldBeAbleToCreateCustomer()
    {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id,name,email);

        // act
        Customer actual = customerController.create(name,email);

        // assert
        assertEquals(expected,actual);
    }
}
