package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.InMemoryModule;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryCustomerRepositoryTest {
    InMemoryCustomerRepository inMemoryCustomerRepository;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new InMemoryModule());
        inMemoryCustomerRepository = injector.getInstance(InMemoryCustomerRepository.class);
    }

    @Test
    void shouldBeAbleToAddCustomerIntoTable() {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id, name, email);

        // act
        Customer actual = inMemoryCustomerRepository.add(expected);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetCustomerById() {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id, name, email);

        // act
        inMemoryCustomerRepository.add(expected);
        Customer actual = inMemoryCustomerRepository.findCustomer(id);

        // assert
        assertEquals(expected, actual);
    }
}