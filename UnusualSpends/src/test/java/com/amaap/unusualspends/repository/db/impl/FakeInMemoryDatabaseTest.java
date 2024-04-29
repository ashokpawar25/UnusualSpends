package com.amaap.unusualspends.repository.db.impl;

import com.amaap.unusualspends.InMemoryModule;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
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
import static org.junit.jupiter.api.Assertions.assertNull;

class FakeInMemoryDatabaseTest {
    FakeInMemoryDatabase fakeInMemoryDatabase;
    CreditCardService creditCardService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new InMemoryModule());
        fakeInMemoryDatabase = injector.getInstance(FakeInMemoryDatabase.class);
        creditCardService = injector.getInstance(CreditCardService.class);
    }

    @Test
    void shouldBeAbleToAddCustomerIntoTable() {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id, name, email);

        // act
        Customer actual = fakeInMemoryDatabase.insertIntoCustomerTable(expected);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetCustomerById() {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id, name, email);

        // act
        fakeInMemoryDatabase.insertIntoCustomerTable(expected);
        Customer actual = fakeInMemoryDatabase.findCustomer(id);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToCreateCreditCard() throws InvalidCreditCardIdException {
        // arrange
        CreditCard creditCard = new CreditCard(1);
        int expected = 1;

        // act
        int actual = fakeInMemoryDatabase.insertIntoCreditCardTable(creditCard);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetCreditCardById() throws InvalidCreditCardIdException {
        // arrange
        int id = 1;
        CreditCard expected = new CreditCard(id);

        // act
        fakeInMemoryDatabase.insertIntoCreditCardTable(expected);
        CreditCard actual = fakeInMemoryDatabase.findCreditCard(id);

        // assert
        assertEquals(expected, actual);
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
        int actual = fakeInMemoryDatabase.insertIntoTransactionTable(transaction);

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
        fakeInMemoryDatabase.insertIntoTransactionTable(expected);
        Transaction actual = fakeInMemoryDatabase.selectTransaction(1);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetNullWhenTransactionIsNotFoundInToDatabase() {
        // arrange & act
        Transaction actual = fakeInMemoryDatabase.selectTransaction(1);

        // assert
        assertNull(actual);
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
        fakeInMemoryDatabase.insertIntoTransactionTable(transaction1);
        fakeInMemoryDatabase.insertIntoTransactionTable(transaction2);
        fakeInMemoryDatabase.insertIntoTransactionTable(transaction3);
        List<Transaction> actual = fakeInMemoryDatabase.getAllTransactions();

        // assert
        assertEquals(expected, actual);
    }
}