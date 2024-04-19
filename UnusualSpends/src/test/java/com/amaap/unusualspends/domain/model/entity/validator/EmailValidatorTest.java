package com.amaap.unusualspends.domain.model.entity.validator;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidEmailIdException;
import org.junit.jupiter.api.Assertions;
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
    void shouldBeAbleToValidateIncorrectEmailId() {
        assertFalse(isValidEmail("ashok"));
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