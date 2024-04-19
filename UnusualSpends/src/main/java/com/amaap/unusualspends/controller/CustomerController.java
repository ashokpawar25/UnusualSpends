package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.domain.model.entity.Customer;

public class CustomerController {
    public Customer create(String name, String email) {
        return new Customer(1,name,email);
    }


}
