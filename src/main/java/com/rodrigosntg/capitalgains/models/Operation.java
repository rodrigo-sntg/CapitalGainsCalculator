package com.rodrigosntg.capitalgains.models;
import com.rodrigosntg.capitalgains.portfolio.Portfolio;

import java.math.BigDecimal;

public abstract class Operation {
    protected BigDecimal unitCost;
    protected int quantity;

    protected Operation(BigDecimal unitCost, int quantity) {
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public int getQuantity() {
        return quantity;
    }


    public abstract BigDecimal process(Portfolio portfolio);
}
