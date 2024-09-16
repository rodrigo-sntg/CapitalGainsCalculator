package com.rodrigosntg.capitalgains.tax;
import com.rodrigosntg.capitalgains.portfolio.Portfolio;

import java.math.BigDecimal;
/**
 * Calcula o imposto devido com base no lucro ou prejuízo da venda de ativos.
 * Se tiver lucro, o imposto é calculado com base no lucro.
 * Se tiver prejuízo, o prejuízo é acumulado.
 * Se tiver prejuízo acumulado, o imposto é calculado com base no lucro após a perda.
 * Se o lucro após a perda for menor ou igual a zero, então a perda é acumulada.
 * Se o lucro após a perda for maior que zero, então a perda acumulada é zerada.
 * Se o valor total da venda for maior que R$ 20.000,00, então o imposto é de 20%.
 *
 */
public class DefaultTaxCalculator implements TaxCalculator {

    @Override
    public BigDecimal calculateTax(BigDecimal profitOrLoss, BigDecimal sellTotal, Portfolio portfolio) {
        if (!isProfit(profitOrLoss)) {
            accumulateLoss(profitOrLoss, portfolio);
            return BigDecimal.ZERO;
        }

        return calculateTaxForProfit(profitOrLoss, sellTotal, portfolio);
    }

    private boolean isProfit(BigDecimal profitOrLoss) {
        return profitOrLoss.compareTo(BigDecimal.ZERO) >= 0;
    }

    private BigDecimal calculateTaxForProfit(BigDecimal profitOrLoss, BigDecimal sellTotal, Portfolio portfolio) {
        BigDecimal accumulatedLoss = portfolio.getAccumulatedLoss();

        if (hasAccumulatedLoss(accumulatedLoss)) {
            return handleProfitWithAccumulatedLoss(profitOrLoss, sellTotal, portfolio, accumulatedLoss);
        }

        return handleProfitWithoutAccumulatedLoss(profitOrLoss, sellTotal);
    }

    private boolean hasAccumulatedLoss(BigDecimal accumulatedLoss) {
        return accumulatedLoss.compareTo(BigDecimal.ZERO) > 0;
    }

    private BigDecimal handleProfitWithAccumulatedLoss(BigDecimal profitOrLoss, BigDecimal sellTotal, Portfolio portfolio, BigDecimal accumulatedLoss) {
        BigDecimal profitAfterLoss = profitOrLoss.subtract(accumulatedLoss);

        if (!isProfitAfterLossPositive(profitAfterLoss)) {
            accumulateRemainingLoss(profitOrLoss, portfolio, accumulatedLoss);
            return BigDecimal.ZERO;
        }

        portfolio.setAccumulatedLoss(BigDecimal.ZERO);
        return calculateTaxIfSellTotalExceedsLimit(profitAfterLoss, sellTotal);
    }

    private boolean isProfitAfterLossPositive(BigDecimal profitAfterLoss) {
        return profitAfterLoss.compareTo(BigDecimal.ZERO) > 0;
    }

    private BigDecimal calculateTaxIfSellTotalExceedsLimit(BigDecimal profitAfterLoss, BigDecimal sellTotal) {
        if (sellTotalExceedsLimit(sellTotal)) {
            return calculateTaxOnProfit(profitAfterLoss);
        }

        return BigDecimal.ZERO;
    }

    private boolean sellTotalExceedsLimit(BigDecimal sellTotal) {
        return sellTotal.compareTo(new BigDecimal("20000.00")) > 0;
    }

    private BigDecimal calculateTaxOnProfit(BigDecimal profit) {
        return profit.multiply(new BigDecimal("0.20"));
    }

    private void accumulateRemainingLoss(BigDecimal profitOrLoss, Portfolio portfolio, BigDecimal accumulatedLoss) {
        portfolio.setAccumulatedLoss(accumulatedLoss.subtract(profitOrLoss));
    }

    private BigDecimal handleProfitWithoutAccumulatedLoss(BigDecimal profitOrLoss, BigDecimal sellTotal) {
        if (sellTotalExceedsLimit(sellTotal)) {
            return calculateTaxOnProfit(profitOrLoss);
        }

        return BigDecimal.ZERO;
    }

    private void accumulateLoss(BigDecimal profitOrLoss, Portfolio portfolio) {
        portfolio.addAccumulatedLoss(profitOrLoss.abs());
    }
}