package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardServiceTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    CreditCardRepository creditCardRepository = new InMemoryCreditCardRepository(inMemoryDatabase);
    CreditCardService creditCardService = new CreditCardService(creditCardRepository);
    @Test
    void shouldBeAbleToCreateCreditCard() throws InvalidCreditCardIdException {
        // arrange
        int expected = 1;

        // act
        int actual = creditCardService.create();

        // assert
        assertEquals(expected,actual);
    }
}