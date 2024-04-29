package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidEmailIdException;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailBodyException;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailSubjectException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailSenderTest {

    @Test
    void shouldBeAbleToThrowExceptionWhenInvalidEmailIdIsPassed() {
        assertThrows(InvalidEmailIdException.class, () -> EmailSender.sendEmail("email subject", "email body", "ashokpawargmail.com"));
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenInvalidEmailSubjectIsPassed() {
        assertThrows(InvalidEmailSubjectException.class, () -> EmailSender.sendEmail("", "email body", "ashokpawar@gmail.com"));
        assertThrows(InvalidEmailSubjectException.class, () -> EmailSender.sendEmail(null, "email body", "ashokpawar@gmail.com"));
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenInvalidEmailBodyIsPassed() {
        assertThrows(InvalidEmailBodyException.class, () -> EmailSender.sendEmail("Regarding unusual spend", "", "ashokpawar@gmail.com"));
        assertThrows(InvalidEmailBodyException.class, () -> EmailSender.sendEmail("Regarding unusual spend", null, "ashokpawar@gmail.com"));
    }

    @Test
    void shouldBeAbleToCreateInstanceOfClass() {
        // arrange
        EmailSender emailSender = new EmailSender();

        // act & assert
        assertNotNull(emailSender);
    }
}