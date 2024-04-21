package com.amaap.unusualspends.domain.model.entity.exception;

public class InvalidTransactionAmountException extends InvalidTransactionDataException {
    public InvalidTransactionAmountException(String message) {
        super(message);
    }
}
