package com.amaap.unusualspends.domain.model.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpendRecordDtoTest {

    @Test
    void shouldBeAbleToCompareInstanceOfClass() {
        // arrange
        SpendRecordDto dto1 = new SpendRecordDto(Category.GROCERIES, 100, 50);
        SpendRecordDto dto2 = new SpendRecordDto(Category.GROCERIES, 100, 50);
        SpendRecordDto dto3 = new SpendRecordDto(Category.TRAVEL, 100, 50);
        SpendRecordDto dto4 = new SpendRecordDto(Category.GROCERIES, 200, 50);
        SpendRecordDto dto5 = new SpendRecordDto(Category.GROCERIES, 100, 70);
        SpendRecordDto dto6 = new SpendRecordDto(Category.TRAVEL, 300, 150);
        Object object = new Object();

        // act & assert
        assertTrue(dto1.equals(dto1));
        assertTrue(dto1.equals(dto2));
        assertFalse(dto1.equals(dto3));
        assertFalse(dto1.equals(dto4));
        assertFalse(dto1.equals(dto5));
        assertFalse(dto1.equals(dto6));
        assertFalse(dto1.equals(object));
        assertFalse(dto1.equals(null));
    }
}