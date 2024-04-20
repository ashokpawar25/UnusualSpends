package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCreditCardRepositoryTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    InMemoryCreditCardRepository inMemoryCreditCardRepository = new InMemoryCreditCardRepository(inMemoryDatabase);
    @Test
    void shouldBeAbleToCreateCreditCard() throws InvalidCreditCardIdException {
        // arrange
        int expected = 1;
        CreditCard creditCard = new CreditCard(1);

        // act
        int actual = inMemoryCreditCardRepository.add(creditCard);

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToGetCreditCardById() throws InvalidCreditCardIdException {
        // arrange
        int id = 1;
        CreditCard expected = new CreditCard(id);

        // act
        inMemoryCreditCardRepository.add(expected);
        CreditCard actual = inMemoryCreditCardRepository.find(id);

        // assert
        assertEquals(expected,actual);
    }
}