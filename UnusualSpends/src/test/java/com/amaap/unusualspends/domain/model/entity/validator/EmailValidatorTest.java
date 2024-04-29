package com.amaap.unusualspends.domain.model.entity.validator;

import org.junit.jupiter.api.Test;

import static com.amaap.unusualspends.domain.model.entity.validator.EmailValidator.*;
import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @Test
    void shouldBeAbleToValidateValidEmailId() {
        assertTrue(isValidEmailId("ashokpawar@gmail.com"));
    }

    @Test
    void shouldBeAbleToValidateAnEmptyEmail() {
        assertTrue(isEmptyEmailId(""));
    }

    @Test
    void shouldBeAbleToValidateNullEmailId() {
        assertTrue(isNullEmailId(null));
    }

    @Test
    void shouldBeAbleToCreateInstanceOfClass() {
        // arrange
        EmailValidator emailValidator = new EmailValidator();

        // act & assert
        assertNotNull(emailValidator);
    }

    @Test
    void shouldBeAbleToValidateIncorrectEmailId() {
        assertFalse(EmailValidator.isValidEmail(null));
        assertFalse(EmailValidator.isValidEmail(""));
        assertFalse(EmailValidator.isValidEmail("ashok"));
        assertFalse(isValidEmail("ashok@"));
        assertFalse(isValidEmail("@gmail"));
        assertFalse(isValidEmail("."));
        assertFalse(isValidEmail("@"));
        assertFalse(isValidEmail("@."));
        assertFalse(isValidEmail("ashok@gmail"));
        assertFalse(isValidEmail(".com"));
        assertFalse(isValidEmail("ashok@gmail."));
        assertFalse(isValidEmail("ashok.com"));
        assertFalse(isValidEmail("@ashok.com"));
        assertFalse(isValidEmail("    @ashok.com"));
        assertFalse(isValidEmail("com.gmail@"));
        assertFalse(isValidEmail("@ashok.com  "));
        assertFalse(isValidEmail("@ash   ok.com"));
    }
}