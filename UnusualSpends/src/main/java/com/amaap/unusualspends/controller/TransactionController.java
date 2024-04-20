package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.service.TransactionService;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;

import java.time.LocalDate;

public class TransactionController {
    private TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public Response create(int cardId, double amount, Category category, LocalDate date) {
        try{
            transactionService.create(cardId,amount,category,date);
            return new Response(HttpStatus.OK,"Transaction created successfully");
        } catch (CreditCardNotFoundException e) {
            return new Response(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
