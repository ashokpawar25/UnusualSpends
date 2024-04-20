package com.amaap.unusualspends.domain.model.entity.builder;

import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.valueobject.Category;

import java.time.LocalDate;
import java.util.List;

public class TransactionBuilder {
    public static List<Transaction> getTransactions() {
        Transaction transaction1 = Transaction.create(1,1,200, Category.GROCERIES, LocalDate.of(2024,4,20));
        Transaction transaction2 = Transaction.create(2,1,300,Category.TRAVEL,LocalDate.of(2024,4,22));
        Transaction transaction3 = Transaction.create(3,2,400,Category.GROCERIES,LocalDate.of(2024,4,23));
        return List.of(transaction1,transaction2,transaction3);
    }
}
