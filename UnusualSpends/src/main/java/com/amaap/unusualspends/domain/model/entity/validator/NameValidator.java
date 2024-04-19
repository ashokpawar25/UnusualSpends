package com.amaap.unusualspends.domain.model.entity.validator;

public class NameValidator {
    public static boolean isValidName(String name) {
        return name.matches("[a-zA-z]{3,} [a-zA-z]{3,}");
    }
}
