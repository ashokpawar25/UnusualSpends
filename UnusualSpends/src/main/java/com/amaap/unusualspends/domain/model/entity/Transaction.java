package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.*;
import com.amaap.unusualspends.domain.model.entity.validator.TransactionValidator;
import com.amaap.unusualspends.domain.model.valueobject.Category;

import java.time.LocalDate;
import java.util.Objects;

import static com.amaap.unusualspends.domain.model.entity.validator.TransactionValidator.*;

public class Transaction {
    private final int cardId;
    private final double amount;
    private final Category category;
    private final LocalDate date;
    private int id;

    public Transaction(int transactionId, int cardId, double amount, Category category, LocalDate date) {
        this.id = transactionId;
        this.cardId = cardId;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public static Transaction create(int id, int cardId, double amount, Category category, LocalDate date) throws InvalidTransactionDataException, InvalidCreditCardIdException {
        if (!TransactionValidator.isValidId(id))
            throw new InvalidTransactionIdException("Invalid Transaction id:" + id);
        if (!isValidCardId(cardId)) throw new InvalidCreditCardIdException("Invalid Credit card id:" + cardId);
        if (!isValidAmount(amount)) throw new InvalidTransactionAmountException("Invalid Transaction amount:" + amount);
        if (!isValidCategory(category))
            throw new InvalidTransactionCategoryException("Invalid Transaction category:" + category);
        if (!isValidDate(date)) throw new InvalidDateException("Invalid Transaction Date:" + date);
        return new Transaction(id, cardId, amount, category, date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardId() {
        return cardId;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && cardId == that.cardId && Double.compare(amount, that.amount) == 0 && category == that.category && Objects.equals(date, that.date);
    }
}
