package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.repository.CreditCardRepository;

public class CreditCardService {
    CreditCardRepository creditCardRepository;
    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public int create() throws InvalidCreditCardIdException {
        return creditCardRepository.add(new CreditCard(1));
    }
}
