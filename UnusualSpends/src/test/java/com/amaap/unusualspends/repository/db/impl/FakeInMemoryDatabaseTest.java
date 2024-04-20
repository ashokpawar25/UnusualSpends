package com.amaap.unusualspends.repository.db.impl;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import com.amaap.unusualspends.service.CreditCardService;
import com.amaap.unusualspends.service.CustomerService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FakeInMemoryDatabaseTest {
    FakeInMemoryDatabase fakeInMemoryDatabase = new FakeInMemoryDatabase();
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    CustomerRepository customerRepository = new InMemoryCustomerRepository(inMemoryDatabase);
    CustomerService customerService = new CustomerService(customerRepository);
    CreditCardRepository creditCardRepository = new InMemoryCreditCardRepository(inMemoryDatabase);
    CreditCardService creditCardService = new CreditCardService(creditCardRepository,customerService);
    @Test
    void shouldBeAbleToAddCustomerIntoTable()
    {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id,name,email);

        // act
        Customer actual = fakeInMemoryDatabase.insertIntoCustomerTable(expected);

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToGetCustomerById()
    {
        // arrange
        int id = 1;
        String name = "Ashok Pawar";
        String email = "ashokpawar@gmail.com";
        Customer expected = new Customer(id,name,email);

        // act
        fakeInMemoryDatabase.insertIntoCustomerTable(expected);
        Customer actual = fakeInMemoryDatabase.findCustomer(id);

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToCreateCreditCard() throws InvalidCreditCardIdException {
        // arrange
        CreditCard creditCard = new CreditCard(1);
        int expected = 1;

        // act
        int actual = fakeInMemoryDatabase.insertIntoCreditCardTable(creditCard);

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToGetCreditCardById() throws InvalidCreditCardIdException {
        // arrange
        int id = 1;
        CreditCard expected = new CreditCard(id);

        // act
        fakeInMemoryDatabase.insertIntoCreditCardTable(expected);
        CreditCard actual = fakeInMemoryDatabase.findCreditCard(id);

        // assert
        assertEquals(expected,actual);
    }

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
        int actual = fakeInMemoryDatabase.insertIntoTransactionTable(transaction);

        // assert
        assertEquals(expected,actual);
    }
}