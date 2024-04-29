package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidEmailIdException;
import com.amaap.unusualspends.domain.model.valueobject.SpendRecordDto;
import com.amaap.unusualspends.domain.service.EmailSender;
import com.amaap.unusualspends.domain.service.SpendAnalyzer;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailBodyException;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailSubjectException;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

import static com.amaap.unusualspends.domain.service.EmailComposer.composeEmail;

public class CreditCardCompanyService {
    private final CreditCardService creditCardService;

    @Inject
    public CreditCardCompanyService(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    public Map<Integer, List<SpendRecordDto>> analyzeSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage) {
        return SpendAnalyzer.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);

    }

    public boolean sendEmail(Map<Integer, List<SpendRecordDto>> spendRecord) {
        try {
            for (Map.Entry<Integer, List<SpendRecordDto>> entry : spendRecord.entrySet()) {
                int cardId = entry.getKey();
                CreditCard creditCard = creditCardService.find(cardId);
                String email = creditCard.getCustomer().getEmail();
                String name = creditCard.getCustomer().getName();
                String subject = "Regarding unusual spend for current month";
                List<SpendRecordDto> record = entry.getValue();
                String body = composeEmail(name, record);
                EmailSender.sendEmail(subject, body, email);
            }
        } catch (InvalidEmailIdException | InvalidEmailSubjectException | CreditCardNotFoundException |
                 InvalidEmailBodyException e) {
            return false;
        }
        return true;
    }
}
