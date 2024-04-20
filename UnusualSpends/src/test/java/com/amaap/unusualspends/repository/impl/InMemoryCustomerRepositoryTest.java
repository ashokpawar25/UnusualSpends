package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCustomerRepositoryTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    InMemoryCustomerRepository inMemoryCustomerRepository = new InMemoryCustomerRepository(inMemoryDatabase);

    @Test
    void shouldBeAbleToAddCustomerIntoTable()
    {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id,name,email);

        // act
        Customer actual = inMemoryCustomerRepository.add(expected);

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToGetCustomerById()
    {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id,name,email);

        // act
        inMemoryCustomerRepository.add(expected);
        Customer actual = inMemoryCustomerRepository.findCustomer(id);

        // assert
        assertEquals(expected,actual);
    }
}