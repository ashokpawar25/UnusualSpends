package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    void shouldBeAbleToCreateCustomer() throws InvalidCustomerDataException {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id,name,email);

        // act
        Customer actual = Customer.create(1,name,email);

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenCustomerIdIsInvalid()
    {
        assertThrows(InvalidIdException.class,()->Customer.create(0,"Ashok Pawar","ashokpawar@gmail.com"));
        assertThrows(InvalidIdException.class,()->Customer.create(-1,"Ashok Pawar","ashokpawar@gmail.com"));
    }
}