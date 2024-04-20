package com.amaap.unusualspends.repository;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;

public interface CreditCardRepository {
    int add(CreditCard creditCard) throws InvalidCreditCardIdException;

    CreditCard find(int id);
}
