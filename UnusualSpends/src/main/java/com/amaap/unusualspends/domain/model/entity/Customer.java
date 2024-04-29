package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerNameException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidEmailIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidIdException;

import java.util.Objects;

import static com.amaap.unusualspends.domain.model.entity.validator.EmailValidator.isValidEmail;
import static com.amaap.unusualspends.domain.model.entity.validator.NameValidator.isValidName;

public class Customer {
    private final String name;
    private final String email;
    private int id;

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static Customer create(int id, String name, String email) throws InvalidCustomerDataException {
        if (!isValidId(id)) throw new InvalidIdException("Customer id should be grater than 0 ");
        if (!isValidName(name)) throw new InvalidCustomerNameException("Invalid customer name " + name);
        if (!isValidEmail(email)) throw new InvalidEmailIdException("Invalid email id " + email);
        return new Customer(id, name, email);
    }

    private static boolean isValidId(int id) {
        return id > 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
    }
}
