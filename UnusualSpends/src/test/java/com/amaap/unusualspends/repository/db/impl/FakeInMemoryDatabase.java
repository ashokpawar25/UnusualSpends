package com.amaap.unusualspends.repository.db.impl;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;

import java.util.ArrayList;
import java.util.List;

public class FakeInMemoryDatabase implements InMemoryDatabase {
    private List<Customer> customers = new ArrayList<>();
    private List<CreditCard> creditCards = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private int customerIdCounter = 1;
    private int creditCardIdCounter = 1;
    private int transactinIdCounter = 1;
    @Override
    public Customer insertIntoCustomerTable(Customer customer) {
        customer.setId(customerIdCounter++);
        customers.add(customer);
        return customer;
    }

    @Override
    public Customer findCustomer(int id) {
        return customers.stream().filter(customer -> customer.getId()==id).findFirst().orElse(null);
    }

    @Override
    public int insertIntoCreditCardTable(CreditCard creditCard) {
        creditCard.setId(creditCardIdCounter++);
        creditCards.add(creditCard);
        return creditCard.getId();
    }

    @Override
    public CreditCard findCreditCard(int id) {
        return creditCards.stream().filter(card -> card.getId() == id).findFirst().orElse(null);
    }

    @Override
    public int insertIntoTransactionTable(Transaction transaction) {
        transaction.setId(transactinIdCounter++);
        transactions.add(transaction);
        return transaction.getId();
    }

    @Override
    public Transaction selectTransaction(int id) {
        return transactions.stream().filter(transaction -> transaction.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactions;
    }
}
