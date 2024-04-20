package com.amaap.unusualspends.repository;

import com.amaap.unusualspends.domain.model.entity.Transaction;

public interface TransactionRepository {
    int add(Transaction transaction);
}
