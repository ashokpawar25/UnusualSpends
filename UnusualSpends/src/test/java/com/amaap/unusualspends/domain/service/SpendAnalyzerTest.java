package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.InMemoryModule;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionDataException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.model.valueobject.SpendRecordDto;
import com.amaap.unusualspends.service.CreditCardService;
import com.amaap.unusualspends.service.TransactionService;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SpendAnalyzerTest {
    CreditCardService creditCardService;
    TransactionService transactionService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new InMemoryModule());
        creditCardService = injector.getInstance(CreditCardService.class);
        transactionService = injector.getInstance(TransactionService.class);
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
        Map<Integer, List<SpendRecordDto>> actual = SpendAnalyzer.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToCreateInstanceOfClass() {
        // arrange
        SpendAnalyzer spendAnalyzer = new SpendAnalyzer();

        // act & assert
        assertNotNull(spendAnalyzer);
    }
}