package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;

public class CreditCardService {
    CreditCardRepository creditCardRepository;
    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public int create() throws InvalidCreditCardIdException {
        return creditCardRepository.add(new CreditCard(1));
    }

    public CreditCard find(int id) throws CreditCardNotFoundException {
        CreditCard creditCard = creditCardRepository.find(id);
        if(creditCard == null) throw new CreditCardNotFoundException("Credit card with id:"+id+" not found");
        return creditCard;
    }
}
