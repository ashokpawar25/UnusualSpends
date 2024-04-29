package com.amaap.unusualspends.domain.model.entity.validator;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.amaap.unusualspends.domain.model.entity.validator.TransactionValidator.*;
import static org.junit.jupiter.api.Assertions.*;

class TransactionValidatorTest {

    @Test
    void shouldBeAbleToValidateTransactionId() {
        assertTrue(isValidId(1));
        assertFalse(isValidId(0));
        assertFalse(isValidId(-1));
    }

    @Test
    void shouldBeAbleToValidateCreditCardId() {
        assertTrue(isValidCardId(1));
        assertFalse(isValidCardId(0));
        assertFalse(isValidCardId(-1));
    }

    @Test
    void shouldBeAbleToValidateTransactionAmount() {
        assertTrue(isValidAmount(1));
        assertFalse(isValidAmount(0));
        assertFalse(isValidAmount(-1));
    }

    @Test
    void shouldBeAbleToValidateTransactionCategory() {
        assertFalse(isValidCategory(null));
    }

    @Test
    void shouldBeAbleToValidateTransactionDate() {
        assertTrue(isValidDate(LocalDate.of(2024, 4, 22)));
        assertFalse(isValidDate(null));
    }

    @Test
    void ShouldBeAbleToCreateInstanceOfClass() {
        // arrange
        TransactionValidator transactionValidator = new TransactionValidator();

        // act & assert
        assertNotNull(transactionValidator);
    }

}