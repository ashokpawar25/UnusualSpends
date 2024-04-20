package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.builder.TransactionBuilder;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.TransactionRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import com.amaap.unusualspends.repository.impl.InMemoryTransactionRepository;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import com.amaap.unusualspends.service.exception.TransactionNotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.amaap.unusualspends.domain.model.entity.builder.TransactionBuilder.getTransactions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionServiceTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    CustomerRepository customerRepository = new InMemoryCustomerRepository(inMemoryDatabase);
    CustomerService customerService = new CustomerService(customerRepository);
    CreditCardRepository creditCardRepository = new InMemoryCreditCardRepository(inMemoryDatabase);
    CreditCardService creditCardService = new CreditCardService(creditCardRepository, customerService);
    TransactionRepository transactionRepository = new InMemoryTransactionRepository(inMemoryDatabase);
    TransactionService transactionService = new TransactionService(creditCardService, transactionRepository);

    @Test
    void shouldBeAbleToCreateTransactionForCreditCard() throws CreditCardNotFoundException, InvalidCreditCardIdException {
        // arrange
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        int expected = 1;

        // act
        creditCardService.create();
        int actual = transactionService.create(cardId, amount, category, date);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetTransactionById() throws CreditCardNotFoundException, InvalidCreditCardIdException, TransactionNotFoundException {
        // arrange
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        Transaction expected = Transaction.create(1, cardId, amount, category, date);

        // act
        creditCardService.create();
        transactionService.create(cardId, amount, category, date);
        Transaction actual = transactionService.find(1);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToThrowExceptionIfTransactionIsNotFoundIntoDatabase() {
        assertThrows(TransactionNotFoundException.class, () -> transactionService.find(1));
    }

    @Test
    void shouldBeAbleToGetAllTransactions() throws InvalidCreditCardIdException, CreditCardNotFoundException {
        // arrange
        List<Transaction> expected = getTransactions();

        // act
        creditCardService.create();
        creditCardService.create();
        transactionService.create(1,200,Category.GROCERIES,LocalDate.of(2024,4,20));
        transactionService.create(1,300,Category.TRAVEL,LocalDate.of(2024,4,22));
        transactionService.create(2,400,Category.GROCERIES,LocalDate.of(2024,4,23));
        List<Transaction> actual = transactionService.getAllTransactions();

        // assert
        assertEquals(expected,actual);
    }
}