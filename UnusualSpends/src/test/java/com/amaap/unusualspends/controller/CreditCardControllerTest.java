package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.unusualspends.service.CreditCardService;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardControllerTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    CreditCardRepository creditCardRepository = new InMemoryCreditCardRepository(inMemoryDatabase);
    CreditCardService creditCardService = new CreditCardService(creditCardRepository);
    CreditCardController creditCardController = new CreditCardController(creditCardService);
    @Test
    void shouldBeAbleToGetOkResponseWhenCreditCardCreatedSuccessfully()
    {
        // arrange
        Response expected = new Response(HttpStatus.OK,"Credit card created successfully");

        // act
        Response actual = creditCardController.create();

        // assert
        assertEquals(expected,actual);
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
        assertEquals(expected,actual);
    }
}
