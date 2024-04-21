package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.domain.model.valueobject.SpendRecordDto;

import java.util.List;

public class EmailComposer {
    public static String composeEmail(String name, List<SpendRecordDto> record) {
        StringBuilder body = new StringBuilder(" \n hello "+ name +"!\n We have detected unusually high spending on your card in these categories:\n ");
        double totalUnusualSpend = 0;
        double totalUsualSpend = 0;
        for (SpendRecordDto spend:record)
        {
            body.append(" * You spent "+ spend.unusualSpendAmount +" on "+ spend.category+"\n");
            totalUnusualSpend += spend.unusualSpendAmount;
            totalUsualSpend += spend.usualSpendAmount;
        }
        body.append("Thanks,\n" +
                "\n" +
                "The Credit Card Company\n");
        body.insert(0,"Total Unusual spending of "+ totalUnusualSpend +" detected!\nYour usual spending of last month was "+totalUsualSpend);
        return body.toString();
    }
}
