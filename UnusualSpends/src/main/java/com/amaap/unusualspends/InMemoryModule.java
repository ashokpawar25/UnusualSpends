package com.amaap.unusualspends;

import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.TransactionRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import com.amaap.unusualspends.repository.impl.InMemoryTransactionRepository;
import com.google.inject.AbstractModule;

public class InMemoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CustomerRepository.class).to(InMemoryCustomerRepository.class);
        bind(CustomerRepository.class).to(InMemoryCustomerRepository.class);
        bind(CreditCardRepository.class).to(InMemoryCreditCardRepository.class);
        bind(TransactionRepository.class).to(InMemoryTransactionRepository.class);
        bind(InMemoryDatabase.class).to(FakeInMemoryDatabase.class).asEagerSingleton();
    }
}
