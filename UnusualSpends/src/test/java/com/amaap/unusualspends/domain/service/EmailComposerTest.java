package com.amaap.unusualspends.domain.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmailComposerTest {

    @Test
    void shouldBeAbleToCreateInstanceOfClass() {
        // arrange
        EmailComposer emailComposer = new EmailComposer();

        // act & assert
        assertNotNull(emailComposer);
    }
}