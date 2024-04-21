package com.amaap.unusualspends.domain.model.entity.exception;

public class InvalidTransactionDataException extends Exception {
    public InvalidTransactionDataException(String message) {
        super(message);
    }
}
