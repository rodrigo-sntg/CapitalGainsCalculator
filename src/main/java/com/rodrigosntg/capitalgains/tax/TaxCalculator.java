package com.rodrigosntg.capitalgains.tax;
import com.rodrigosntg.capitalgains.portfolio.Portfolio;

import java.math.BigDecimal;

public interface TaxCalculator {

    BigDecimal calculateTax(BigDecimal profitOrLoss, BigDecimal sellTotal, Portfolio portfolio);
}
