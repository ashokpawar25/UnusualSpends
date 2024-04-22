package com.amaap.unusualspends;

import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import com.google.inject.AbstractModule;

public class InMemoryModule extends AbstractModule {
    @Override
    protected void configure()
    {
        bind(CustomerRepository.class).to(InMemoryCustomerRepository.class);
        bind(InMemoryDatabase.class).to(FakeInMemoryDatabase.class);
    }
}
