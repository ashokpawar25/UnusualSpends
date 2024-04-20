package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.service.TransactionService;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import com.amaap.unusualspends.service.exception.TransactionNotFoundException;

import java.time.LocalDate;
import java.util.List;

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

    public Transaction find(int id) throws TransactionNotFoundException {
        return transactionService.find(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
