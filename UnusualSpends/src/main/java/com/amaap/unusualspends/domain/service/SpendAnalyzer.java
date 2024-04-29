package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.model.valueobject.SpendRecordDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpendAnalyzer {
    public static Map<Integer, List<SpendRecordDto>> analyzeSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage) {
        double criteria = 1 + (thresholdPercentage / 100);
        Map<Integer, List<SpendRecordDto>> spendRecords = new HashMap<>();
        for (Transaction currentTransaction : currentMonthTransactions) {
            for (Transaction previousTransaction : previousMonthTransactions) {
                if (currentTransaction.getCategory() == previousTransaction.getCategory() &&
                        currentTransaction.getCardId() == previousTransaction.getCardId() &&
                        currentTransaction.getAmount() > previousTransaction.getAmount() * criteria) {

                    int cardId = currentTransaction.getCardId();
                    Category category = currentTransaction.getCategory();
                    double unusualSpendAmount = currentTransaction.getAmount();
                    double usualSpendAmount = previousTransaction.getAmount();
                    SpendRecordDto spendRecord = new SpendRecordDto(category, unusualSpendAmount, usualSpendAmount);
                    List<SpendRecordDto> spendRecordList;
                    if (spendRecords.containsKey(cardId)) {
                        spendRecordList = spendRecords.get(cardId);

                    } else {
                        spendRecordList = new ArrayList<>();
                    }
                    spendRecordList.add(spendRecord);
                    spendRecords.put(cardId, spendRecordList);
                }
            }
        }
        return spendRecords;
    }
}
