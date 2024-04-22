package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import com.amaap.unusualspends.service.CreditCardService;
import com.amaap.unusualspends.service.CustomerService;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.amaap.unusualspends.domain.model.entity.builder.TransactionBuilder.getTransactions;
import static org.junit.jupiter.api.Assertions.*;

public class CreditCardControllerTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();
    CustomerRepository customerRepository = new InMemoryCustomerRepository(inMemoryDatabase);
    CustomerService customerService = new CustomerService(customerRepository);
    CreditCardRepository creditCardRepository = new InMemoryCreditCardRepository(inMemoryDatabase);
    CreditCardService creditCardService = new CreditCardService(creditCardRepository,customerService);
    CreditCardController creditCardController = new CreditCardController(creditCardService);
    @Test
    void shouldBeAbleToGetOkResponseWhenCreditCardCreatedSuccessfully()
    {
        // arrange
        Response expected = new Response(HttpStatus.OK,"Credit card created successfully");

        // act
        Response actual = creditCardController.create();

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToGetCreditCardById() throws InvalidCreditCardIdException, CreditCardNotFoundException {
        // arrange
        int id = 1;
        CreditCard expected = new CreditCard(id);

        // act
        creditCardController.create();
        CreditCard actual = creditCardController.find(id);

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToThrowExceptionWhenCreditCardNotFoundIntoDatabase()
    {
        assertThrows(CreditCardNotFoundException.class,()->creditCardController.find(1));
    }

    @Test
    void shouldBeAbleToGetOkResponseWhenCardIsAssignedToCreditCard() throws InvalidCreditCardIdException, InvalidCustomerDataException {
        // arrange
        int cardId = 1;
        int customerId = 1;
        Response expected = new Response(HttpStatus.OK,"Card is Assigned to customer successfully");

        // act
        creditCardService.create();
        customerService.create("Ashok Pawar","ashokpawar@gmail.com");
        Response actual = creditCardController.mapCustomer(cardId,customerId);

        // assert
        assertEquals(expected,actual);
    }

    @Test
    void shouldBeAbleToGetNotFoundResponseWhenCardOrCustomerIsNotFound() throws InvalidCreditCardIdException, InvalidCustomerDataException {
        // arrange
        int cardId = 1;
        int customerId = 2;
        Response expected = new Response(HttpStatus.NOT_FOUND,"Error mapping customer");

        // act
        creditCardService.create();
        customerService.create("Ashok Pawar","ashokpawar@gmail.com");
        Response actual = creditCardController.mapCustomer(cardId,customerId);

        // assert
        assertEquals(expected,actual);
    }
}
