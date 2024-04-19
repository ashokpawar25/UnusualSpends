package com.amaap.unusualspends.domain.model.entity.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.amaap.unusualspends.domain.model.entity.validator.NameValidator.isValidName;
import static org.junit.jupiter.api.Assertions.*;

class NameValidatorTest {

    @Test
    void shouldBeAbleToValidateCorrectNameOfCustomer()
    {
        assertTrue(isValidName("Ashok Pawar"));
        assertTrue(isValidName("Ash Paw"));
    }

    @Test
    void shouldBeAbleToValidateIncorrectNameOfCustomer()
    {
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
}