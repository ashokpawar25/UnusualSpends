package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.InMemoryModule;
import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionDataException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.service.CreditCardService;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import com.amaap.unusualspends.service.exception.TransactionNotFoundException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.amaap.unusualspends.domain.model.entity.builder.TransactionBuilder.getTransactions;
import static com.amaap.unusualspends.domain.model.entity.builder.TransactionBuilder.getTransactionsForCurrentMonth;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionControllerTest {
    CreditCardService creditCardService;
    TransactionController transactionController;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new InMemoryModule());
        creditCardService = injector.getInstance(CreditCardService.class);
        transactionController = injector.getInstance(TransactionController.class);
    }

    @Test
    void shouldBeAbleToGetOkResponseWhenCreateTransaction() throws InvalidCreditCardIdException {
        // arrange
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        Response expected = new Response(HttpStatus.OK, "Transaction created successfully");

        // act
        creditCardService.create();
        Response actual = transactionController.create(cardId, amount, category, date);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleGetNotFoundResponseWhenInvalidCreditCardIdIsPassed() throws InvalidCreditCardIdException {
        // arrange
        int cardId = 2;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        Response expected = new Response(HttpStatus.NOT_FOUND, "Credit card with id:" + cardId + " not found");

        // act
        creditCardService.create();
        Response actual = transactionController.create(cardId, amount, category, date);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleGetBadRequestAsResponseWhenInvalidTransactionDataIsPassed() throws InvalidCreditCardIdException {
        // arrange
        int cardId = 1;
        double amount = -100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        Response expected = new Response(HttpStatus.BAD_REQUEST, "Invalid Transaction amount:" + amount);

        // act
        creditCardService.create();
        Response actual = transactionController.create(cardId, amount, category, date);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetTransactionById() throws InvalidCreditCardIdException, TransactionNotFoundException, InvalidTransactionDataException {
        // arrange
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        Transaction expected = Transaction.create(1, cardId, amount, category, date);

        // act
        creditCardService.create();
        transactionController.create(cardId, amount, category, date);
        Transaction actual = transactionController.find(1);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToThrowExceptionIfTransactionIsNotFoundIntoDatabase() {
        assertThrows(TransactionNotFoundException.class, () -> transactionController.find(1));
    }

    @Test
    void shouldBeAbleToGetAllTransactions() throws InvalidCreditCardIdException, CreditCardNotFoundException, InvalidTransactionDataException {
        // arrange
        List<Transaction> expected = getTransactions();

        // act
        creditCardService.create();
        creditCardService.create();
        transactionController.create(1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 20));
        transactionController.create(1, 300, Category.TRAVEL, LocalDate.of(2024, 4, 22));
        transactionController.create(2, 400, Category.GROCERIES, LocalDate.of(2024, 4, 23));
        List<Transaction> actual = transactionController.getAllTransactions();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToFilterTransactionsByMonth() throws InvalidCreditCardIdException, CreditCardNotFoundException, InvalidTransactionDataException {
        // arrange
        List<Transaction> expected = getTransactionsForCurrentMonth();
        Month currentMonth = LocalDate.now().getMonth();
        Month prevMonth = currentMonth.minus(1);
        int currentYear = LocalDate.now().getYear();
        int prevYear = currentYear;
        if (currentMonth == Month.JANUARY) {
            prevMonth = Month.DECEMBER;
            prevYear = prevYear--;
        }

        // act
        creditCardService.create();
        creditCardService.create();
        transactionController.create(1, 200, Category.GROCERIES, LocalDate.of(currentYear, currentMonth, 20));
        transactionController.create(1, 300, Category.TRAVEL, LocalDate.of(prevYear, prevMonth, 22));
        transactionController.create(2, 400, Category.GROCERIES, LocalDate.of(currentYear, currentMonth, 23));
        List<Transaction> actual = transactionController.filterTransactionsByMonth(currentMonth);

        // assert
        assertEquals(expected, actual);
    }
}
