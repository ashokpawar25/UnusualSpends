package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerNameException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidEmailIdException;
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
        Customer expected = new Customer(id, name, email);

        // act
        Customer actual = Customer.create(1, name, email);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenCustomerIdIsInvalid() {
        assertThrows(InvalidIdException.class, () -> Customer.create(0, "Ashok Pawar", "ashokpawar@gmail.com"));
        assertThrows(InvalidIdException.class, () -> Customer.create(-1, "Ashok Pawar", "ashokpawar@gmail.com"));
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenCustomerNameIsInvalid() {
        assertThrows(InvalidCustomerNameException.class, () -> Customer.create(1, "A P", "ashokpawar@gmail.com"));
        assertThrows(InvalidCustomerNameException.class, () -> Customer.create(2, "As P", "ashokpawar@gmail.com"));
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenCustomerEmailIdIsInvalid() {
        assertThrows(InvalidEmailIdException.class, () -> Customer.create(1, "Ashok Pawar", "ashokpawar  @gmail.com"));
        assertThrows(InvalidEmailIdException.class, () -> Customer.create(2, "Ashok Pawar", "ashokpawa"));
    }

    @Test
    void shouldBeAbleToCompareInstanceOfClass() {
        // arrange
        Customer customer1 = new Customer(1, "ABC XYZ", "abc@gmail.com");
        Customer customer2 = new Customer(1, "ABC XYZ", "abc@gmail.com");
        Customer customer3 = new Customer(2, "ABC XYZ", "abc@gmail.com");
        Customer customer4 = new Customer(1, "ABCD XYZ", "abc@gmail.com");
        Customer customer5 = new Customer(1, "ABC XYZ", "abdec@gmail.com");
        Customer customer6 = new Customer(3, "ABCD XYZW", "abcde@gmail.com");
        Object object = new Object();

        // act & assert
        assertTrue(customer1.equals(customer1));
        assertTrue(customer1.equals(customer2));
        assertFalse(customer1.equals(customer3));
        assertFalse(customer1.equals(customer4));
        assertFalse(customer1.equals(customer5));
        assertFalse(customer1.equals(customer6));
        assertFalse(customer1.equals(object));
        assertFalse(customer1.equals(null));
    }
}