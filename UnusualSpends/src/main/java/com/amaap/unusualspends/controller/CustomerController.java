package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.service.CustomerService;

public class CustomerController {
    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer create(String name, String email) {
        return customerService.create(name,email);
    }


}
