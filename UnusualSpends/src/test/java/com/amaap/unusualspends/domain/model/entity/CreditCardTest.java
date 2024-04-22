package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import org.checkerframework.checker.units.qual.C;
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
    void shouldBeAbleToThrowExceptionWhenInvalidIdIsPassed()
    {
        assertThrows(InvalidCreditCardIdException.class,()-> new CreditCard(0));
        assertThrows(InvalidCreditCardIdException.class,()-> new CreditCard(-1));
    }

}