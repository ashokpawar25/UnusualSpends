package com.amaap.unusualspends.domain.model.entity.validator;

import com.amaap.unusualspends.domain.model.valueobject.Category;

import java.time.LocalDate;
import java.util.EnumSet;

public class TransactionValidator {
    public static boolean isValidCardId(int cardId) {
        return cardId > 0;
    }

    public static boolean isValidId(int id) {
        return id > 0;
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    public static boolean isValidCategory(Category category) {
        return EnumSet.allOf(Category.class).contains(category);
    }

    public static boolean isValidDate(LocalDate date) {
        return date != null;
    }
}
