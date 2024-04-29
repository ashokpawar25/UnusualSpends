package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.*;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void shouldBeAbleToCreateTransaction() throws InvalidTransactionDataException, InvalidCreditCardIdException {
        // arrange
        int transactionId = 1;
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        Transaction expected = new Transaction(transactionId, cardId, amount, category, date);

        // act
        Transaction actual = Transaction.create(transactionId, cardId, amount, category, date);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenTransactionIdIsInvalid() {
        assertThrows(InvalidTransactionIdException.class, () -> Transaction.create(0, 1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 21)));
        assertThrows(InvalidTransactionIdException.class, () -> Transaction.create(-1, 1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 21)));
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenCardIdIsInvalid() {
        assertThrows(InvalidCreditCardIdException.class, () -> Transaction.create(1, 0, 200, Category.GROCERIES, LocalDate.of(2024, 4, 21)));
        assertThrows(InvalidCreditCardIdException.class, () -> Transaction.create(2, -1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 21)));
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenAmountIsInvalid() {
        assertThrows(InvalidTransactionAmountException.class, () -> Transaction.create(1, 1, 0, Category.GROCERIES, LocalDate.of(2024, 4, 21)));
        assertThrows(InvalidTransactionAmountException.class, () -> Transaction.create(2, 1, -1, Category.GROCERIES, LocalDate.of(2024, 4, 21)));
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenCategoryIsInvalid() {
        assertThrows(InvalidTransactionCategoryException.class, () -> Transaction.create(1, 1, 200, null, LocalDate.of(2024, 4, 21)));
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenDateIsInvalid() {
        assertDoesNotThrow(() -> Transaction.create(1, 1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 21)));
        assertThrows(InvalidDateException.class, () -> Transaction.create(2, 1, 100, Category.GROCERIES, null));
    }

    @Test
    void shouldBeAbleToCompareInstanceOfClass() {
        // arrange
        Transaction transaction1 = new Transaction(1, 1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 22));
        Transaction transaction2 = new Transaction(1, 1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 22));
        Transaction transaction3 = new Transaction(2, 1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 22));
        Transaction transaction4 = new Transaction(1, 2, 200, Category.GROCERIES, LocalDate.of(2024, 4, 22));
        Transaction transaction5 = new Transaction(1, 1, 300, Category.GROCERIES, LocalDate.of(2024, 4, 22));
        Transaction transaction6 = new Transaction(1, 1, 200, Category.TRAVEL, LocalDate.of(2024, 4, 22));
        Transaction transaction7 = new Transaction(1, 1, 200, Category.GROCERIES, LocalDate.of(2024, 5, 23));
        Transaction transaction8 = new Transaction(3, 3, 400, Category.TRAVEL, LocalDate.of(2024, 6, 23));
        Object object = new Object();

        // act & assert
        assertTrue(transaction1.equals(transaction1));
        assertTrue(transaction1.equals(transaction2));
        assertFalse(transaction1.equals(transaction3));
        assertFalse(transaction1.equals(transaction4));
        assertFalse(transaction1.equals(transaction5));
        assertFalse(transaction1.equals(transaction6));
        assertFalse(transaction1.equals(transaction7));
        assertFalse(transaction1.equals(transaction8));
        assertFalse(transaction1.equals(object));
        assertFalse(transaction1.equals(null));
    }
}