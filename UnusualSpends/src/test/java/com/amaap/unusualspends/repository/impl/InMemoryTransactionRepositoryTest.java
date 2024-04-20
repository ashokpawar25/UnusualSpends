package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.service.CreditCardService;
import com.amaap.unusualspends.service.CustomerService;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTransactionRepositoryTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    CustomerRepository customerRepository = new InMemoryCustomerRepository(inMemoryDatabase);
    CustomerService customerService = new CustomerService(customerRepository);
    CreditCardRepository creditCardRepository = new InMemoryCreditCardRepository(inMemoryDatabase);
    CreditCardService creditCardService = new CreditCardService(creditCardRepository,customerService);
    InMemoryTransactionRepository inMemoryTransactionRepository = new InMemoryTransactionRepository(inMemoryDatabase);

    @Test
    void shouldBeAbleToAddTransactionForCreditCard() throws InvalidCreditCardIdException {
        // arrange
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024,4,20);
        int expected = 1;

        // act
        creditCardService.create();
        Transaction transaction = Transaction.create(1,cardId,amount,category,date);
        int actual = inMemoryTransactionRepository.add(transaction);

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToGetTransactionById() throws InvalidCreditCardIdException {
        // arrange
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024,4,20);
        Transaction expected = Transaction.create(1,cardId,amount,category,date);

        // act
        creditCardService.create();
        inMemoryTransactionRepository.add(expected);
        Transaction actual = inMemoryTransactionRepository.find(1);

        // assert
        assertEquals(expected,actual);
    }
}