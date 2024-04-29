package com.amaap.unusualspends.domain.model.entity.validator;

import org.junit.jupiter.api.Test;

import static com.amaap.unusualspends.domain.model.entity.validator.NameValidator.*;
import static org.junit.jupiter.api.Assertions.*;

class NameValidatorTest {

    @Test
    void shouldBeAbleToValidateValidNameOfCustomer() {
        assertTrue(isValidName("Ashok Pawar"));
        assertTrue(isValidName("Ash Paw"));
    }

    @Test
    void shouldBeAbleToValidateAnEmptyNameOfCustomer() {
        assertTrue(isEmptyName(""));
    }

    @Test
    void shouldBeAbleToValidateNullNameOfCustomer() {
        assertTrue(isNullName(null));
    }

    @Test
    void shouldBeAbleToValidateIncorrectNameOfCustomer() {
        assertFalse(isValidName(null));
        assertFalse(isValidName(""));
        assertFalse(isValidName("A b"));
        assertFalse(isValidName("A ab"));
        assertFalse(isValidName("A abc"));
        assertFalse(isValidName("Ab ab"));
        assertFalse(isValidName("Ab abc"));
        assertFalse(isValidName("Ab a"));
        assertFalse(isValidName("Abc a"));
        assertFalse(isValidName("Abc ab"));
        assertFalse(isValidName("Abc abc  "));
        assertFalse(isValidName("Abc  ab"));
        assertFalse(isValidName("Abc @ab"));
        assertFalse(isValidName("Abcab"));
        assertFalse(isValidName("Abca@b"));
        assertFalse(isValidName(" rAbca@b"));
    }

    @Test
    void shouldBeAbleToCreateInstanceOfClass() {
        // arrange
        NameValidator nameValidator = new NameValidator();

        // act & assert
        assertNotNull(nameValidator);
    }
}