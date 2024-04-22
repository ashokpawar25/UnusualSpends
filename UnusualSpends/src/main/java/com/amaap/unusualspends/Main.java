package com.amaap.unusualspends;

import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new InMemoryModule());
        InMemoryDatabase inMemoryDatabase = injector.getInstance(FakeInMemoryDatabase.class);
        CustomerRepository customerRepository = injector.getInstance(InMemoryCustomerRepository.class);
    }
}
