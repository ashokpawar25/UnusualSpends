package com.amaap.unusualspends.service.exception;

public class CreditCardNotFoundException extends Exception {
    public CreditCardNotFoundException(String message) {
        super(message);
    }
}
