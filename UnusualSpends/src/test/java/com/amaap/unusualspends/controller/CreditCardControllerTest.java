package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.InMemoryModule;
import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.service.CustomerService;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreditCardControllerTest {
    CustomerService customerService;
    CreditCardController creditCardController;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new InMemoryModule());
        customerService = injector.getInstance(CustomerService.class);
        creditCardController = injector.getInstance(CreditCardController.class);
    }

    @Test
    void shouldBeAbleToGetOkResponseWhenCreditCardCreatedSuccessfully() {
        // arrange
        Response expected = new Response(HttpStatus.OK, "Credit card created successfully");

        // act
        Response actual = creditCardController.create();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetCreditCardById() throws InvalidCreditCardIdException, CreditCardNotFoundException {
        // arrange
        int id = 1;
        CreditCard expected = new CreditCard(id);

        // act
        creditCardController.create();
        CreditCard actual = creditCardController.find(id);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenCreditCardNotFoundIntoDatabase() {
        assertThrows(CreditCardNotFoundException.class, () -> creditCardController.find(1));
    }

    @Test
    void shouldBeAbleToGetOkResponseWhenCardIsAssignedToCreditCard() throws InvalidCreditCardIdException, InvalidCustomerDataException {
        // arrange
        int cardId = 1;
        int customerId = 1;
        Response expected = new Response(HttpStatus.OK, "Card is Assigned to customer successfully");

        // act
        creditCardController.create();
        customerService.create("Ashok Pawar", "ashokpawar@gmail.com");
        Response actual = creditCardController.mapCustomer(cardId, customerId);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetNotFoundResponseWhenCardOrCustomerIsNotFound() throws InvalidCreditCardIdException, InvalidCustomerDataException {
        // arrange
        int cardId = 1;
        int customerId = 2;
        Response expected = new Response(HttpStatus.NOT_FOUND, "Error mapping customer");

        // act
        creditCardController.create();
        customerService.create("Ashok Pawar", "ashokpawar@gmail.com");
        Response actual = creditCardController.mapCustomer(cardId, customerId);

        // assert
        assertEquals(expected, actual);
    }
}
