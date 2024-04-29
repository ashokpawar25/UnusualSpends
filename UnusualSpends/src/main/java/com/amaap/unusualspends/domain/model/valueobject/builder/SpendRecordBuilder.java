package com.amaap.unusualspends.domain.model.valueobject.builder;

import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.model.valueobject.SpendRecordDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpendRecordBuilder {
    public static Map<Integer, List<SpendRecordDto>> getSpendRecords() {
        Map<Integer, List<SpendRecordDto>> spendRecords = new HashMap<>();
        List<SpendRecordDto> records = new ArrayList<>();
        records.add(new SpendRecordDto(Category.GROCERIES, 400, 100));
        records.add(new SpendRecordDto(Category.TRAVEL, 600, 200));
        spendRecords.put(1, records);
        return spendRecords;
    }
}
