package com.rodrigosntg.capitalgains.models;

import com.rodrigosntg.capitalgains.portfolio.Portfolio;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BuyOperation extends Operation {

    public BuyOperation(BigDecimal unitCost, int quantity) {
        super(unitCost, quantity);
    }

    @Override
    public BigDecimal process(Portfolio portfolio) {
        portfolio.buy(unitCost, quantity);
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }
}
