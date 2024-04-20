package com.amaap.unusualspends.repository.db;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.Transaction;

public interface InMemoryDatabase {
    Customer insertIntoCustomerTable(Customer customer);

    Customer findCustomer(int id);

    int insertIntoCreditCardTable(CreditCard creditCard);

    CreditCard findCreditCard(int id);

    int insertIntoTransactionTable(Transaction transaction);

    Transaction selectTransaction(int id);
}
