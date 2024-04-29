package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;

import java.util.Objects;

public class CreditCard {
    private int id;
    private Customer customer;

    public CreditCard(int id) throws InvalidCreditCardIdException {
        if (id <= 0) throw new InvalidCreditCardIdException("Id of credit card should be greater than 0");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return id == that.id && Objects.equals(customer, that.customer);
    }
}
