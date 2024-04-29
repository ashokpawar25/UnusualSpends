package com.amaap.unusualspends.service;

import com.amaap.unusualspends.InMemoryModule;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import com.amaap.unusualspends.service.exception.CustomerNotFoundException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardServiceTest {
    CustomerService customerService;
    CreditCardService creditCardService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new InMemoryModule());
        customerService = injector.getInstance(CustomerService.class);
        creditCardService = injector.getInstance(CreditCardService.class);
    }

    @Test
    void shouldBeAbleToCreateCreditCard() throws InvalidCreditCardIdException {
        // arrange
        int expected = 1;

        // act
        int actual = creditCardService.create();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetCreditCardById() throws InvalidCreditCardIdException, CreditCardNotFoundException {
        // arrange
        int id = 1;
        CreditCard expected = new CreditCard(id);

        // act
        creditCardService.create();
        CreditCard actual = creditCardService.find(id);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenCreditCardNotFoundIntoDatabase() {
        assertThrows(CreditCardNotFoundException.class, () -> creditCardService.find(1));
    }

    @Test
    void shouldBeAbleToAssignCreditCardToCustomer() throws InvalidCreditCardIdException, InvalidCustomerDataException, CustomerNotFoundException, CreditCardNotFoundException {
        // arrange
        int cardId = 1;
        int customerId = 1;

        // act
        creditCardService.create();
        customerService.create("Ashok Pawar", "ashokpawar@gmail.com");
        boolean actual = creditCardService.mapCustomer(cardId, customerId);

        // assert
        assertTrue(actual);
    }
}