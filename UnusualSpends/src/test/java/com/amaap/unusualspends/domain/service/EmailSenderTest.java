package com.amaap.unusualspends.domain.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailSenderTest {
    @Test
    void shouldBeAbleToCreateInstanceOfClass()
    {
        // arrange
        EmailSender emailSender = new EmailSender();

        // act & assert
        assertNotNull(emailSender);
    }
}