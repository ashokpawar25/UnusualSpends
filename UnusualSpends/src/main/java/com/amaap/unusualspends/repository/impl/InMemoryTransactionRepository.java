package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.repository.TransactionRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;

public class InMemoryTransactionRepository implements TransactionRepository {
    InMemoryDatabase inMemoryDatabase;
    public InMemoryTransactionRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public int add(Transaction transaction) {
        return inMemoryDatabase.insertIntoTransactionTable(transaction);
    }

    @Override
    public Transaction find(int id) {
        return inMemoryDatabase.selectTransaction(id);
    }
}
