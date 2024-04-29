package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.InMemoryModule;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryCreditCardRepositoryTest {
    InMemoryCreditCardRepository inMemoryCreditCardRepository;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new InMemoryModule());
        inMemoryCreditCardRepository = injector.getInstance(InMemoryCreditCardRepository.class);
    }

    @Test
    void shouldBeAbleToCreateCreditCard() throws InvalidCreditCardIdException {
        // arrange
        int expected = 1;
        CreditCard creditCard = new CreditCard(1);

        // act
        int actual = inMemoryCreditCardRepository.add(creditCard);

        // assert
        assertEquals(expected, actual);
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
        assertEquals(expected, actual);
    }
}