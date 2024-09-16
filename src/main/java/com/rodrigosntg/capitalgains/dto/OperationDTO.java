package com.rodrigosntg.capitalgains.dto;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

public class OperationDTO {
    @SerializedName("operation")
    private String operation;

    @SerializedName("unit-cost")
    private BigDecimal unitCost;

    @SerializedName("quantity")
    private int quantity;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public int getQuantity() {
        return quantity;
    }

}

