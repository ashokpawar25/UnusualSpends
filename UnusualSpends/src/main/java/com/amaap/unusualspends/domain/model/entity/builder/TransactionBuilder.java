package com.amaap.unusualspends.domain.model.entity.builder;

import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionDataException;
import com.amaap.unusualspends.domain.model.valueobject.Category;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class TransactionBuilder {
    public static List<Transaction> getTransactions() throws InvalidTransactionDataException, InvalidCreditCardIdException {
        Transaction transaction1 = Transaction.create(1, 1, 200, Category.GROCERIES, LocalDate.of(2024, 4, 20));
        Transaction transaction2 = Transaction.create(2, 1, 300, Category.TRAVEL, LocalDate.of(2024, 4, 22));
        Transaction transaction3 = Transaction.create(3, 2, 400, Category.GROCERIES, LocalDate.of(2024, 4, 23));
        return List.of(transaction1, transaction2, transaction3);
    }


    public static List<Transaction> getTransactionsForCurrentMonth() throws InvalidTransactionDataException, InvalidCreditCardIdException {
        Month currentMonth = LocalDate.now().getMonth();
        int currentYear = LocalDate.now().getYear();

        Transaction transaction1 = Transaction.create(1, 1, 200, Category.GROCERIES, LocalDate.of(currentYear, currentMonth, 20));
        Transaction transaction2 = Transaction.create(3, 2, 400, Category.GROCERIES, LocalDate.of(currentYear, currentMonth, 23));
        return List.of(transaction1, transaction2);
    }
}
