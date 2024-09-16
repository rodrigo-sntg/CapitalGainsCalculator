package com.rodrigosntg.capitalgains.portfolio;
import com.rodrigosntg.capitalgains.tax.TaxCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Portfolio {
    private BigDecimal totalQuantity = BigDecimal.ZERO;
    private BigDecimal averagePrice = BigDecimal.ZERO;
    private BigDecimal accumulatedLoss = BigDecimal.ZERO;
    private final TaxCalculator taxCalculator;

    public Portfolio(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }


    public void buy(BigDecimal unitCost, int quantity) {
        BigDecimal quantityBD = BigDecimal.valueOf(quantity);
        BigDecimal totalCost = averagePrice.multiply(totalQuantity).add(unitCost.multiply(quantityBD));
        totalQuantity = totalQuantity.add(quantityBD);
        if (totalQuantity.compareTo(BigDecimal.ZERO) > 0) {
            averagePrice = totalCost.divide(totalQuantity, 2, RoundingMode.HALF_UP);
        } else {
            averagePrice = BigDecimal.ZERO;
        }
    }


    public BigDecimal sell(BigDecimal unitCost, int quantity) {
        BigDecimal quantityBD = BigDecimal.valueOf(quantity);
        BigDecimal sellTotal = unitCost.multiply(quantityBD);
        BigDecimal costBasis = averagePrice.multiply(quantityBD);
        BigDecimal profitOrLoss = sellTotal.subtract(costBasis);
        totalQuantity = totalQuantity.subtract(quantityBD);

        return taxCalculator.calculateTax(profitOrLoss, sellTotal, this);
    }

    public BigDecimal getAccumulatedLoss() {
        return accumulatedLoss;
    }

    public void setAccumulatedLoss(BigDecimal accumulatedLoss) {
        this.accumulatedLoss = accumulatedLoss;
    }

    public void addAccumulatedLoss(BigDecimal loss) {
        this.accumulatedLoss = this.accumulatedLoss.add(loss);
    }

}
