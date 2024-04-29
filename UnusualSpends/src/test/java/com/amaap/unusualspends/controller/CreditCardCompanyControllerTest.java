package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.InMemoryModule;
import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionDataException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.model.valueobject.SpendRecordDto;
import com.amaap.unusualspends.service.CreditCardService;
import com.amaap.unusualspends.service.CustomerService;
import com.amaap.unusualspends.service.TransactionService;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import com.amaap.unusualspends.service.exception.CustomerNotFoundException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

import static com.amaap.unusualspends.domain.model.valueobject.builder.SpendRecordBuilder.getSpendRecords;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardCompanyControllerTest {

    CustomerService customerService;
    CreditCardService creditCardService;
    TransactionService transactionService;
    CreditCardCompanyController creditCardCompanyController;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new InMemoryModule());
        creditCardService = injector.getInstance(CreditCardService.class);
        transactionService = injector.getInstance(TransactionService.class);
        creditCardCompanyController = injector.getInstance(CreditCardCompanyController.class);
        customerService = injector.getInstance(CustomerService.class);
    }

    @Test
    void shouldBeAbleToFindUnusualSpend() throws InvalidCreditCardIdException, InvalidTransactionDataException, CreditCardNotFoundException {
        // arrange
        Map<Integer, List<SpendRecordDto>> expected = getSpendRecords();
        double thresholdPercentage = 20;
        Month currentMonth = LocalDate.now().getMonth();
        Month prevMonth = currentMonth.minus(1);
        int currentYear = LocalDate.now().getYear();
        int prevYear = currentMonth == Month.JANUARY ? currentYear - 1 : currentYear;

        // act
        creditCardService.create();
        transactionService.create(1, 400, Category.GROCERIES, LocalDate.of(currentYear, currentMonth, 20));
        transactionService.create(1, 600, Category.TRAVEL, LocalDate.of(currentYear, currentMonth, 22));
        transactionService.create(1, 100, Category.GROCERIES, LocalDate.of(prevYear, prevMonth, 23));
        transactionService.create(1, 200, Category.TRAVEL, LocalDate.of(prevYear, prevMonth, 22));
        List<Transaction> currentMonthTransactions = transactionService.filterTransactionsByMonth(currentMonth);
        List<Transaction> previousMonthTransactions = transactionService.filterTransactionsByMonth(prevMonth);
        Map<Integer, List<SpendRecordDto>> actual = creditCardCompanyController.evaluateSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void shouldBeAbleToGetOkResponseWhenEmailSendToCustomer() throws InvalidCreditCardIdException, InvalidTransactionDataException, CreditCardNotFoundException, InvalidCustomerDataException, CustomerNotFoundException {
        // arrange
        double thresholdPercentage = 20;
        Month currentMonth = LocalDate.now().getMonth();
        Month prevMonth = currentMonth.minus(1);
        int currentYear = LocalDate.now().getYear();
        int prevYear = currentMonth == Month.JANUARY ? currentYear - 1 : currentYear;
        Response expected = new Response(HttpStatus.OK, "Email sent successfully");

        // act
        customerService.create("Ashok Pawar", "ashokpawar8020@gmail.com");
        creditCardService.create();
        creditCardService.mapCustomer(1, 1);
        transactionService.create(1, 400, Category.GROCERIES, LocalDate.of(currentYear, currentMonth, 20));
        transactionService.create(1, 600, Category.TRAVEL, LocalDate.of(currentYear, currentMonth, 22));
        transactionService.create(1, 100, Category.GROCERIES, LocalDate.of(prevYear, prevMonth, 23));
        transactionService.create(1, 200, Category.TRAVEL, LocalDate.of(prevYear, prevMonth, 22));
        List<Transaction> currentMonthTransactions = transactionService.filterTransactionsByMonth(currentMonth);
        List<Transaction> previousMonthTransactions = transactionService.filterTransactionsByMonth(prevMonth);
        Map<Integer, List<SpendRecordDto>> spendRecord = creditCardCompanyController.evaluateSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);
        Response actual = creditCardCompanyController.sendEmail(spendRecord);

        // assert
        assertEquals(expected, actual);
    }
}
