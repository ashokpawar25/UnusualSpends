package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.valueobject.Category;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    private int id;
    private int cardId;
    private double amount;
    private Category category;
    private LocalDate date;
    public Transaction(int transactionId, int cardId, double amount, Category category, LocalDate date) {
        this.id = transactionId;
        this.cardId = cardId;
        this.amount = amount;
        this.category = category;
        this.date =  date;
    }

    public static Transaction create(int id, int cardId, double amount, Category category, LocalDate date) {
        return new Transaction(id,cardId,amount,category,date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
