package com.amaap.unusualspends.domain.model.entity.validator;

public class NameValidator {
    public static boolean isValidName(String name) {
        if (!isNullName(name) && !isEmptyName(name) && !isInvalidName(name)) {
            return true;
        }
        return false;

    }

    public static boolean isInvalidName(String name) {
        return !name.matches("[a-zA-z]{3,} [a-zA-z]{3,}");
    }

    public static boolean isEmptyName(String name) {
        return name.isEmpty();
    }

    public static boolean isNullName(String name) {
        return name == null;
    }
}
