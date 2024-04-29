package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionDataException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.repository.TransactionRepository;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import com.amaap.unusualspends.service.exception.TransactionNotFoundException;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {
    private final CreditCardService creditCardService;
    private final TransactionRepository transactionRepository;

    @Inject
    public TransactionService(CreditCardService creditCardService, TransactionRepository transactionRepository) {
        this.creditCardService = creditCardService;
        this.transactionRepository = transactionRepository;
    }

    public int create(int cardId, double amount, Category category, LocalDate date) throws CreditCardNotFoundException, InvalidTransactionDataException, InvalidCreditCardIdException {
        CreditCard creditCard = creditCardService.find(cardId);
        if (creditCard == null) {
            return -1;
        }
        Transaction transaction = Transaction.create(1, cardId, amount, category, date);
        return transactionRepository.add(transaction);
    }

    public Transaction find(int id) throws TransactionNotFoundException {
        Transaction transaction = transactionRepository.find(id);
        if (transaction == null) throw new TransactionNotFoundException("Transaction with id:" + id + " not found");
        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }

    public List<Transaction> filterTransactionsByMonth(Month month) {
        List<Transaction> transactions = getAllTransactions();
        return transactions.stream().filter(transaction -> transaction.getDate().getMonth().equals(month)).collect(Collectors.toList());
    }
}
