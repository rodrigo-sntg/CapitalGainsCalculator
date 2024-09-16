package com.rodrigosntg.capitalgains.tax;

import com.rodrigosntg.capitalgains.portfolio.Portfolio;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class DefaultTaxCalculatorTest {

    private DefaultTaxCalculator taxCalculator;
    private Portfolio portfolio;

    @Before
    public void setUp() {
        taxCalculator = new DefaultTaxCalculator();
        portfolio = new Portfolio(taxCalculator);
    }

    @Test
    public void testCalculateTax_NoProfit_LowSellValue() {
        // Caso #1: Venda menor que R$ 20.000,00
        BigDecimal profitOrLoss = new BigDecimal("250.00");
        BigDecimal sellTotal = new BigDecimal("7500.00");
        BigDecimal tax = taxCalculator.calculateTax(profitOrLoss, sellTotal, portfolio);

        // Sem imposto pq o valor da venda é menor que 20.000
        assertEquals(BigDecimal.ZERO, tax);
    }

    @Test
    public void testCalculateTax_WithProfit_SellOver20000() {
        // Caso #2: Venda com lucro acima de R$ 20.000,00
        BigDecimal profitOrLoss = new BigDecimal("50000.00");
        BigDecimal sellTotal = new BigDecimal("100000.00");
        BigDecimal tax = taxCalculator.calculateTax(profitOrLoss, sellTotal, portfolio);

        // 20% de R$ 50.000 = R$ 10.000
        assertEquals(new BigDecimal("10000.00"), tax.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testCalculateTax_WithLoss() {
        // Caso #3: Prejuízo, sem imposto
        BigDecimal profitOrLoss = new BigDecimal("-25000.00");
        BigDecimal sellTotal = new BigDecimal("10000.00");
        BigDecimal tax = taxCalculator.calculateTax(profitOrLoss, sellTotal, portfolio);

        // Sem imposto pq teve prejuízo
        assertEquals(BigDecimal.ZERO, tax);
        // Prejuízo acumulado
        assertEquals(new BigDecimal("25000.00"), portfolio.getAccumulatedLoss());
    }

    @Test
    public void testCalculateTax_ProfitWithAccumulatedLoss() {
        // Caso #4: Lucro, mas com prejuízo acumulado
        portfolio.addAccumulatedLoss(new BigDecimal("20000.00"));

        BigDecimal profitOrLoss = new BigDecimal("50000.00");
        BigDecimal sellTotal = new BigDecimal("80000.00");
        BigDecimal tax = taxCalculator.calculateTax(profitOrLoss, sellTotal, portfolio);

        // Lucro de R$ 50.000 - R$ 20.000 (prejuízo) = R$ 30.000 => 20% de R$ 30.000 = R$ 6.000
        assertEquals(new BigDecimal("6000.00"), tax.setScale(2, RoundingMode.HALF_UP));
        // Prejuízo acumulado foi zerado
        assertEquals(BigDecimal.ZERO, portfolio.getAccumulatedLoss());
    }

    @Test
    public void testCalculateTax_ProfitWithPartiallyRemainingLoss() {
        // Caso #5: Lucro que não zera completamente o prejuízo acumulado
        portfolio.addAccumulatedLoss(new BigDecimal("50000.00"));

        BigDecimal profitOrLoss = new BigDecimal("20000.00");
        BigDecimal sellTotal = new BigDecimal("40000.00");
        BigDecimal tax = taxCalculator.calculateTax(profitOrLoss, sellTotal, portfolio);

        // Lucro de R$ 20.000 é totalmente consumido pelo prejuízo acumulado
        assertEquals(BigDecimal.ZERO, tax);
        // Restam R$ 30.000 de prejuízo acumulado
        assertEquals(new BigDecimal("30000.00"), portfolio.getAccumulatedLoss().setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testCalculateTax_LargeProfitNoLoss() {
        // Caso #6: Lucro grande sem prejuízo acumulado
        BigDecimal profitOrLoss = new BigDecimal("400000.00");
        BigDecimal sellTotal = new BigDecimal("500000.00");
        BigDecimal tax = taxCalculator.calculateTax(profitOrLoss, sellTotal, portfolio);

        // 20% de R$ 400.000 = R$ 80.000
        assertEquals(new BigDecimal("80000.00"), tax.setScale(2, RoundingMode.HALF_UP));
    }
}
