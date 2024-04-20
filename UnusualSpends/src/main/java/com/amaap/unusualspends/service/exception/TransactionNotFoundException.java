package com.amaap.unusualspends.service.exception;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
