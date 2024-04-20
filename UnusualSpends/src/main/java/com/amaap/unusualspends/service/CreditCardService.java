package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import com.amaap.unusualspends.service.exception.CustomerNotFoundException;

public class CreditCardService {
    CreditCardRepository creditCardRepository;
    CustomerService customerService;
    public CreditCardService(CreditCardRepository creditCardRepository,CustomerService customerService) {
        this.creditCardRepository = creditCardRepository;
        this.customerService = customerService;
    }

    public int create() throws InvalidCreditCardIdException {
        return creditCardRepository.add(new CreditCard(1));
    }

    public CreditCard find(int id) throws CreditCardNotFoundException {
        CreditCard creditCard = creditCardRepository.find(id);
        if(creditCard == null) throw new CreditCardNotFoundException("Credit card with id:"+id+" not found");
        return creditCard;
    }

    public boolean mapCustomer(int cardId, int customerId) throws CreditCardNotFoundException, CustomerNotFoundException {
        CreditCard creditCard = find(cardId);
        Customer customer = customerService.find(customerId);
        if(creditCard != null && customer != null)
        {
            creditCard.setCustomer(customer);
            return true;
        }
        return false;
    }
}
