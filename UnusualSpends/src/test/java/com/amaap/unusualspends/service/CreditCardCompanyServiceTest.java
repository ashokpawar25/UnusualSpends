package com.amaap.unusualspends.service;

import com.amaap.unusualspends.controller.CreditCardCompanyController;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionDataException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.model.valueobject.SpendRecordDto;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.TransactionRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import com.amaap.unusualspends.repository.impl.InMemoryTransactionRepository;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import com.amaap.unusualspends.service.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

import static com.amaap.unusualspends.domain.model.valueobject.builder.SpendRecordBuilder.getSpendRecords;
import static org.junit.jupiter.api.Assertions.*;

class CreditCardCompanyServiceTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    CustomerRepository customerRepository = new InMemoryCustomerRepository(inMemoryDatabase);
    CustomerService customerService = new CustomerService(customerRepository);
    CreditCardRepository creditCardRepository = new InMemoryCreditCardRepository(inMemoryDatabase);
    CreditCardService creditCardService = new CreditCardService(creditCardRepository, customerService);
    TransactionRepository transactionRepository = new InMemoryTransactionRepository(inMemoryDatabase);
    TransactionService transactionService = new TransactionService(creditCardService, transactionRepository);
    CreditCardCompanyService creditCardCompanyService = new CreditCardCompanyService(creditCardService);

    @Test
    void shouldBeAbleToFindUnusualSpend() throws InvalidCreditCardIdException, InvalidTransactionDataException, CreditCardNotFoundException {
        // arrange
        Map<Integer, List<SpendRecordDto>> expected = getSpendRecords();
        double thresholdPercentage = 20;
        Month currentMonth = LocalDate.now().getMonth();
        Month prevMonth = currentMonth.minus(1);
        int currentYear = LocalDate.now().getYear();
        int prevYear = currentMonth == Month.JANUARY?currentYear-1:currentYear;

        // act
        creditCardService.create();
        transactionService.create(1,400, Category.GROCERIES,LocalDate.of(currentYear,currentMonth,20));
        transactionService.create(1,600,Category.TRAVEL,LocalDate.of(currentYear,currentMonth,22));
        transactionService.create(1,100,Category.GROCERIES,LocalDate.of(prevYear,prevMonth,23));
        transactionService.create(1,200,Category.TRAVEL,LocalDate.of(prevYear,prevMonth,22));
        List<Transaction> currentMonthTransactions = transactionService.filterTransactionsByMonth(currentMonth);
        List<Transaction> previousMonthTransactions = transactionService.filterTransactionsByMonth(prevMonth);
        Map<Integer, List<SpendRecordDto>> actual = creditCardCompanyService.analyzeSpend(currentMonthTransactions, previousMonthTransactions,thresholdPercentage);

        // assert
        assertEquals(expected,actual);

    }

    @Test
    void shouldBeAbleToSendAnEmailToCustomerUnusualSpend() throws InvalidCreditCardIdException, InvalidTransactionDataException, CreditCardNotFoundException, InvalidCustomerDataException, CustomerNotFoundException {
        // arrange
        double thresholdPercentage = 20;
        Month currentMonth = LocalDate.now().getMonth();
        Month prevMonth = currentMonth.minus(1);
        int currentYear = LocalDate.now().getYear();
        int prevYear = currentMonth == Month.JANUARY?currentYear-1:currentYear;

        // act
        customerService.create("Ashok Pawar","ashokpawar8020@gmail.com");
        creditCardService.create();
        creditCardService.mapCustomer(1,1);
        transactionService.create(1,400, Category.GROCERIES,LocalDate.of(currentYear,currentMonth,20));
        transactionService.create(1,600,Category.TRAVEL,LocalDate.of(currentYear,currentMonth,22));
        transactionService.create(1,100,Category.GROCERIES,LocalDate.of(prevYear,prevMonth,23));
        transactionService.create(1,200,Category.TRAVEL,LocalDate.of(prevYear,prevMonth,22));
        List<Transaction> currentMonthTransactions = transactionService.filterTransactionsByMonth(currentMonth);
        List<Transaction> previousMonthTransactions = transactionService.filterTransactionsByMonth(prevMonth);
        Map<Integer, List<SpendRecordDto>> spendRecord = creditCardCompanyService.analyzeSpend(currentMonthTransactions, previousMonthTransactions,thresholdPercentage);
        boolean isSent = creditCardCompanyService.sendEmail(spendRecord);
        // assert
        assertTrue(isSent);

    }
}