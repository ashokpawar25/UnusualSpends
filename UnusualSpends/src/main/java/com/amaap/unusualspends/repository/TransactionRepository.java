package com.amaap.unusualspends.repository;

import com.amaap.unusualspends.domain.model.entity.Transaction;

import java.util.List;

public interface TransactionRepository {
    int add(Transaction transaction);

    Transaction find(int id);

    List<Transaction> getAllTransactions();
}
