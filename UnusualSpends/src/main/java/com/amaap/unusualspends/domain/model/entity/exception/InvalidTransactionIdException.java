package com.amaap.unusualspends.domain.model.entity.exception;

public class InvalidTransactionIdException extends InvalidTransactionDataException {
    public InvalidTransactionIdException(String message) {
        super(message);
    }
}
