package org.example.service;

import org.example.service.impl.TaxServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class TaxServiceTest {
    private TaxService taxService;

    @BeforeMethod
    public void setUp() {
        taxService = new TaxServiceImpl();
    }

    @Test
    public void testCalculateTax() {
        BigDecimal amount = new BigDecimal("100.00");
        BigDecimal taxRate = new BigDecimal("20.0");
        BigDecimal expectedTax = new BigDecimal("20.00");

        BigDecimal actualTax = taxService.calculateTax(amount, taxRate);
        assertEquals(actualTax, expectedTax, "Tax calculation failed");
    }

    @Test
    public void testCalculateTotalWithTax() {
        BigDecimal amount = new BigDecimal("100.00");
        BigDecimal taxRate = new BigDecimal("20.0");
        BigDecimal expectedTotal = new BigDecimal("120.00");

        BigDecimal actualTotal = taxService.calculateTotalWithTax(amount, taxRate);
        assertEquals(actualTotal, expectedTotal, "Total with tax calculation failed");
    }

    @Test
    public void testCalculateTaxByCategory() {
        BigDecimal amount = new BigDecimal("100.00");
        
        // Test standard rate
        BigDecimal expectedStandardTax = new BigDecimal("20.00");
        BigDecimal actualStandardTax = taxService.calculateTaxByCategory(amount, TaxCategory.STANDARD);
        assertEquals(actualStandardTax, expectedStandardTax, "Standard tax calculation failed");

        // Test reduced rate
        BigDecimal expectedReducedTax = new BigDecimal("5.00");
        BigDecimal actualReducedTax = taxService.calculateTaxByCategory(amount, TaxCategory.REDUCED);
        assertEquals(actualReducedTax, expectedReducedTax, "Reduced tax calculation failed");

        // Test zero rate
        BigDecimal expectedZeroTax = new BigDecimal("0.00");
        BigDecimal actualZeroTax = taxService.calculateTaxByCategory(amount, TaxCategory.ZERO);
        assertEquals(actualZeroTax, expectedZeroTax, "Zero tax calculation failed");

        // Test luxury rate
        BigDecimal expectedLuxuryTax = new BigDecimal("25.00");
        BigDecimal actualLuxuryTax = taxService.calculateTaxByCategory(amount, TaxCategory.LUXURY);
        assertEquals(actualLuxuryTax, expectedLuxuryTax, "Luxury tax calculation failed");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateTaxWithNegativeAmount() {
        BigDecimal negativeAmount = new BigDecimal("-100.00");
        BigDecimal taxRate = new BigDecimal("20.0");
        taxService.calculateTax(negativeAmount, taxRate);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateTaxWithNegativeRate() {
        BigDecimal amount = new BigDecimal("100.00");
        BigDecimal negativeTaxRate = new BigDecimal("-20.0");
        taxService.calculateTax(amount, negativeTaxRate);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateTaxWithNullCategory() {
        BigDecimal amount = new BigDecimal("100.00");
        taxService.calculateTaxByCategory(amount, null);
    }
} 