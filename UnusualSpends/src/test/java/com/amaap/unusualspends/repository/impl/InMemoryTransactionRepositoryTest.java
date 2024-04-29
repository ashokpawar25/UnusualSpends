package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.InMemoryModule;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionDataException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.service.CreditCardService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryTransactionRepositoryTest {
    CreditCardService creditCardService;
    InMemoryTransactionRepository inMemoryTransactionRepository;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new InMemoryModule());
        creditCardService = injector.getInstance(CreditCardService.class);
        inMemoryTransactionRepository = injector.getInstance(InMemoryTransactionRepository.class);
    }

    @Test
    void shouldBeAbleToAddTransactionForCreditCard() throws InvalidCreditCardIdException, InvalidTransactionDataException {
        // arrange
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        int expected = 1;

        // act
        creditCardService.create();
        Transaction transaction = Transaction.create(1, cardId, amount, category, date);
        int actual = inMemoryTransactionRepository.add(transaction);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetTransactionById() throws InvalidCreditCardIdException, InvalidTransactionDataException {
        // arrange
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        Transaction expected = Transaction.create(1, cardId, amount, category, date);

        // act
        creditCardService.create();
        inMemoryTransactionRepository.add(expected);
        Transaction actual = inMemoryTransactionRepository.find(1);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetAllTransactions() throws InvalidCreditCardIdException, InvalidTransactionDataException {
        // arrange
        Transaction transaction1 = Transaction.create(1, 1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 20));
        Transaction transaction2 = Transaction.create(2, 1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 20));
        Transaction transaction3 = Transaction.create(3, 1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 20));
        List<Transaction> expected = List.of(transaction1, transaction2, transaction3);

        // act
        creditCardService.create();
        inMemoryTransactionRepository.add(transaction1);
        inMemoryTransactionRepository.add(transaction2);
        inMemoryTransactionRepository.add(transaction3);
        List<Transaction> actual = inMemoryTransactionRepository.getAllTransactions();

        // assert
        assertEquals(expected, actual);
    }
}