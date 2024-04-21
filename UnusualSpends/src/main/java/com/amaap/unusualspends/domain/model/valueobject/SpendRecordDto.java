package com.amaap.unusualspends.domain.model.valueobject;

public class SpendRecordDto {
    public Category category;
    public double unusualSpendAmount;
    public double usualSpendAmount;

    public SpendRecordDto(Category category, double unusualSpendAmount, double usualSpendAmount) {
        this.category = category;
        this.unusualSpendAmount = unusualSpendAmount;
        this.usualSpendAmount = usualSpendAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpendRecordDto that = (SpendRecordDto) o;
        return Double.compare(unusualSpendAmount, that.unusualSpendAmount) == 0 && Double.compare(usualSpendAmount, that.usualSpendAmount) == 0 && category == that.category;
    }
}
