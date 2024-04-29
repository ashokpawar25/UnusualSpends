package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import jakarta.inject.Inject;

public class InMemoryCreditCardRepository implements CreditCardRepository {
    private final InMemoryDatabase inMemoryDatabase;

    @Inject
    public InMemoryCreditCardRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public int add(CreditCard creditCard) {
        return inMemoryDatabase.insertIntoCreditCardTable(creditCard);
    }

    @Override
    public CreditCard find(int id) {
        return inMemoryDatabase.findCreditCard(id);
    }
}
