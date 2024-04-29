package com.amaap.unusualspends;

import com.amaap.unusualspends.controller.CreditCardCompanyController;
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
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InvalidCustomerDataException, InvalidCreditCardIdException, InvalidTransactionDataException, CreditCardNotFoundException {
        Injector injector = Guice.createInjector(new InMemoryModule());
        CustomerService customerService = injector.getInstance(CustomerService.class);
        CreditCardService creditCardService = injector.getInstance(CreditCardService.class);
        TransactionService transactionService = injector.getInstance(TransactionService.class);
        CreditCardCompanyController creditCardCompanyController = injector.getInstance(CreditCardCompanyController.class);

        double thresholdPercentage = 20;
        Month currentMonth = LocalDate.now().getMonth();
        Month prevMonth = currentMonth.minus(1);
        int currentYear = LocalDate.now().getYear();
        int prevYear = currentMonth == Month.JANUARY ? currentYear - 1 : currentYear;

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
        Response response = creditCardCompanyController.sendEmail(spendRecord);
        System.out.println(response.getMessage());
    }
}
