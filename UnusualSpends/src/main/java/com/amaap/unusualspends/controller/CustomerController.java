package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.unusualspends.service.CustomerService;

public class CustomerController {
    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Response create(String name, String email) {
        try{
            customerService.create(name,email);
            return new Response(HttpStatus.OK,"Customer created successfully");
        } catch (InvalidCustomerDataException exception) {
            return new Response(HttpStatus.BAD_REQUEST,exception.getMessage());
        }
    }


    public Customer find(int id) {
        return customerService.find(id);
    }
}
