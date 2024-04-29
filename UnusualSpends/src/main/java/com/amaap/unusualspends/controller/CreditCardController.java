package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.service.CreditCardService;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import jakarta.inject.Inject;

public class CreditCardController {
    private final CreditCardService creditCardService;

    @Inject
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    public Response create() {
        try {
            creditCardService.create();
            return new Response(HttpStatus.OK, "Credit card created successfully");
        } catch (InvalidCreditCardIdException exception) {
            return new Response(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

    }

    public CreditCard find(int id) throws CreditCardNotFoundException {
        return creditCardService.find(id);
    }

    public Response mapCustomer(int cardId, int customerId) {
        boolean isMapped = creditCardService.mapCustomer(cardId, customerId);
        if (isMapped) {
            return new Response(HttpStatus.OK, "Card is Assigned to customer successfully");
        }
        return new Response(HttpStatus.NOT_FOUND, "Error mapping customer");
    }
}
