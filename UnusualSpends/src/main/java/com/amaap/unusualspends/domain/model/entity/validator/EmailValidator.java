package com.amaap.unusualspends.domain.model.entity.validator;

public class EmailValidator {
    public static boolean isValidEmail(String email) {
        if (!isNullEmailId(email) && !isEmptyEmailId(email) && isValidEmailId(email)) {
            return true;
        }
        return false;
    }

    public static boolean isValidEmailId(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    public static boolean isEmptyEmailId(String email) {
        return email.isEmpty();
    }

    public static boolean isNullEmailId(String email) {
        return email == null;
    }
}
