package com.rodrigosntg.capitalgains.models;

import com.rodrigosntg.capitalgains.portfolio.Portfolio;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SellOperation extends Operation {

    public SellOperation(BigDecimal unitCost, int quantity) {
        super(unitCost, quantity);
    }

    @Override
    public BigDecimal process(Portfolio portfolio) {
        BigDecimal tax = portfolio.sell(unitCost, quantity);
        return tax.setScale(2, RoundingMode.HALF_UP);
    }
}
