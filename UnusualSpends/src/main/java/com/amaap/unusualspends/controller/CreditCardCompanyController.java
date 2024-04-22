package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidEmailIdException;
import com.amaap.unusualspends.domain.model.valueobject.SpendRecordDto;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailBodyException;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailSubjectException;
import com.amaap.unusualspends.service.CreditCardCompanyService;
import com.amaap.unusualspends.service.exception.CreditCardNotFoundException;

import java.util.List;
import java.util.Map;

public class CreditCardCompanyController {
    CreditCardCompanyService creditCardCompanyService;
    public CreditCardCompanyController(CreditCardCompanyService creditCardCompanyService) {
        this.creditCardCompanyService = creditCardCompanyService;
    }

    public Map<Integer, List<SpendRecordDto>> evaluateSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage) {
        return creditCardCompanyService.analyzeSpend(currentMonthTransactions, previousMonthTransactions,thresholdPercentage);
    }

    public Response sendEmail(Map<Integer, List<SpendRecordDto>> spendRecord) {
            boolean isSent = creditCardCompanyService.sendEmail(spendRecord);
            if(isSent){
                return new Response(HttpStatus.OK,"Email sent successfully");
            }
            return new Response(HttpStatus.BAD_REQUEST,"Error sending Email");

    }
}
