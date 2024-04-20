package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.service.CreditCardService;

public class CreditCardController {
    private final CreditCardService creditCardService;
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    public Response create() {
        try {
            creditCardService.create();
            return new Response(HttpStatus.OK,"Credit card created successfully");
        } catch (InvalidCreditCardIdException exception) {
            return new Response(HttpStatus.BAD_REQUEST,exception.getMessage());
        }

    }
}
