package com.amaap.unusualspends.service;

import com.amaap.unusualspends.InMemoryModule;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionDataException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
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

class TransactionServiceTest {
    CreditCardService creditCardService;
    TransactionService transactionService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new InMemoryModule());
        creditCardService = injector.getInstance(CreditCardService.class);
        transactionService = injector.getInstance(TransactionService.class);
    }

    @Test
    void shouldBeAbleToCreateTransactionForCreditCard() throws CreditCardNotFoundException, InvalidCreditCardIdException, InvalidTransactionDataException {
        // arrange
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        int expected = 1;

        // act
        creditCardService.create();
        int actual = transactionService.create(cardId, amount, category, date);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetTransactionById() throws CreditCardNotFoundException, InvalidCreditCardIdException, TransactionNotFoundException, InvalidTransactionDataException {
        // arrange
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        Transaction expected = Transaction.create(1, cardId, amount, category, date);

        // act
        creditCardService.create();
        transactionService.create(cardId, amount, category, date);
        Transaction actual = transactionService.find(1);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToThrowExceptionIfTransactionIsNotFoundIntoDatabase() {
        assertThrows(TransactionNotFoundException.class, () -> transactionService.find(1));
    }

    @Test
    void shouldBeAbleToGetAllTransactions() throws InvalidCreditCardIdException, CreditCardNotFoundException, InvalidTransactionDataException {
        // arrange
        List<Transaction> expected = getTransactions();

        // act
        creditCardService.create();
        creditCardService.create();
        transactionService.create(1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 20));
        transactionService.create(1, 300, Category.TRAVEL, LocalDate.of(2024, 4, 22));
        transactionService.create(2, 400, Category.GROCERIES, LocalDate.of(2024, 4, 23));
        List<Transaction> actual = transactionService.getAllTransactions();

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
        transactionService.create(1, 200, Category.GROCERIES, LocalDate.of(currentYear, currentMonth, 20));
        transactionService.create(1, 300, Category.TRAVEL, LocalDate.of(prevYear, prevMonth, 22));
        transactionService.create(2, 400, Category.GROCERIES, LocalDate.of(currentYear, currentMonth, 23));
        List<Transaction> actual = transactionService.filterTransactionsByMonth(currentMonth);

        // assert
        assertEquals(expected, actual);
    }
}