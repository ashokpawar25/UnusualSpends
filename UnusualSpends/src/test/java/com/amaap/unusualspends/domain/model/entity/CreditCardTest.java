package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {
    @Test
    void shouldBeAbleToCreateCreditCard() throws InvalidCreditCardIdException {
        // arrange
        int id = 1;

        // act
        CreditCard creditCard = new CreditCard(id);

        // assert
        assertNotNull(creditCard);
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenInvalidIdIsPassed() {
        assertThrows(InvalidCreditCardIdException.class, () -> new CreditCard(0));
        assertThrows(InvalidCreditCardIdException.class, () -> new CreditCard(-1));
    }

    @Test
    void shouldBeAbleToCompareInstanceOfClass() throws InvalidCreditCardIdException {
        // arrange
        CreditCard creditCard1 = new CreditCard(1);
        CreditCard creditCard2 = new CreditCard(1);
        CreditCard creditCard3 = new CreditCard(2);
        CreditCard creditCard4 = new CreditCard(1);
        CreditCard creditCard5 = new CreditCard(3);
        Object object = new Object();
        Customer customer1 = new Customer(1, "Ashok", "ashok@gmail.com");
        Customer customer2 = new Customer(1, "Ashok", "ashok@gmail.com");
        Customer customer3 = new Customer(2, "Ashok", "ashok@gmail.com");

        // act
        creditCard1.setCustomer(customer1);
        creditCard2.setCustomer(customer2);
        creditCard3.setCustomer(customer3);
        creditCard4.setCustomer(customer3);
        creditCard5.setCustomer(customer3);

        // assert
        assertTrue(creditCard1.equals(creditCard1));
        assertTrue(creditCard1.equals(creditCard2));
        assertFalse(creditCard1.equals(creditCard3));
        assertFalse(creditCard1.equals(creditCard4));
        assertFalse(creditCard1.equals(creditCard5));
        assertFalse(creditCard1.equals(object));
        assertFalse(creditCard1.equals(null));
    }
}