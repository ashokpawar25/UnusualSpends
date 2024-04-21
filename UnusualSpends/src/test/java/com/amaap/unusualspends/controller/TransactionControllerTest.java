package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.builder.TransactionBuilder;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionDataException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.TransactionRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import com.amaap.unusualspends.repository.impl.InMemoryTransactionRepository;
import com.amaap.unusualspends.service.CreditCardService;
import com.amaap.unusualspends.service.CustomerService;
import com.amaap.unusualspends.service.TransactionService;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import com.amaap.unusualspends.service.exception.TransactionNotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.amaap.unusualspends.domain.model.entity.builder.TransactionBuilder.getTransactions;
import static com.amaap.unusualspends.domain.model.entity.builder.TransactionBuilder.getTransactionsForCurrentMonth;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionControllerTest {

    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    CustomerRepository customerRepository = new InMemoryCustomerRepository(inMemoryDatabase);
    CustomerService customerService = new CustomerService(customerRepository);
    CreditCardRepository creditCardRepository = new InMemoryCreditCardRepository(inMemoryDatabase);
    CreditCardService creditCardService = new CreditCardService(creditCardRepository, customerService);
    TransactionRepository transactionRepository = new InMemoryTransactionRepository(inMemoryDatabase);
    TransactionService transactionService = new TransactionService(creditCardService, transactionRepository);
    TransactionController transactionController = new TransactionController(transactionService);

    @Test
    void shouldBeAbleToGetOkResponseWhenCreateTransaction() throws InvalidCreditCardIdException {
        // arrange
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        Response expected = new Response(HttpStatus.OK, "Transaction created successfully");

        // act
        creditCardService.create();
        Response actual = transactionController.create(cardId, amount, category, date);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleGetNotFoundResponseWhenInvalidCreditCardIdIsPassed() throws InvalidCreditCardIdException {
        // arrange
        int cardId = 2;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        Response expected = new Response(HttpStatus.NOT_FOUND, "Credit card with id:"+cardId+" not found");

        // act
        creditCardService.create();
        Response actual = transactionController.create(cardId, amount, category, date);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetTransactionById() throws InvalidCreditCardIdException, TransactionNotFoundException, InvalidTransactionDataException {
        // arrange
        int cardId = 1;
        double amount = 100;
        Category category = Category.TRAVEL;
        LocalDate date = LocalDate.of(2024, 4, 20);
        Transaction expected = Transaction.create(1, cardId, amount, category, date);

        // act
        creditCardService.create();
        transactionController.create(cardId, amount, category, date);
        Transaction actual = transactionController.find(1);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToThrowExceptionIfTransactionIsNotFoundIntoDatabase() {
        assertThrows(TransactionNotFoundException.class, () -> transactionController.find(1));
    }

    @Test
    void shouldBeAbleToGetAllTransactions() throws InvalidCreditCardIdException, CreditCardNotFoundException, InvalidTransactionDataException {
        // arrange
        List<Transaction> expected = getTransactions();

        // act
        creditCardService.create();
        creditCardService.create();
        transactionController.create(1,200, Category.GROCERIES, LocalDate.of(2024,4,20));
        transactionController.create(1,300,Category.TRAVEL,LocalDate.of(2024,4,22));
        transactionController.create(2,400,Category.GROCERIES,LocalDate.of(2024,4,23));
        List<Transaction> actual = transactionController.getAllTransactions();

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToFilterTransactionsByMonth() throws InvalidCreditCardIdException, CreditCardNotFoundException, InvalidTransactionDataException {
        // arrange
        List<Transaction> expected = getTransactionsForCurrentMonth();
        Month currentMonth = LocalDate.now().getMonth();
        Month prevMonth = currentMonth.minus(1);
        int currentYear = LocalDate.now().getYear();
        int prevYear = currentYear;
        if(currentMonth == Month.JANUARY)
        {
            prevMonth = Month.DECEMBER;
            prevYear = prevYear--;
        }

        // act
        creditCardService.create();
        creditCardService.create();
        transactionController.create(1,200,Category.GROCERIES,LocalDate.of(currentYear,currentMonth,20));
        transactionController.create(1,300,Category.TRAVEL,LocalDate.of(prevYear,prevMonth,22));
        transactionController.create(2,400,Category.GROCERIES,LocalDate.of(currentYear,currentMonth,23));
        List<Transaction> actual = transactionController.filterTransactionsByMonth(currentMonth);

        // assert
        assertEquals(expected,actual);
    }
}
