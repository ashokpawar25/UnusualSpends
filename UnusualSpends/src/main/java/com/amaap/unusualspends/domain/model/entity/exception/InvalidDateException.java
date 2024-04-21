package com.amaap.unusualspends.domain.model.entity.exception;

public class InvalidDateException extends InvalidTransactionDataException {
    public InvalidDateException(String message) {
        super(message);
    }
}
