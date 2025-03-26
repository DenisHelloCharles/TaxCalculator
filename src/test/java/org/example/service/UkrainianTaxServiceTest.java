package org.example.service;

import org.example.service.impl.UkrainianTaxServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UkrainianTaxServiceTest {
    private UkrainianTaxService taxService;

    @BeforeMethod
    public void setUp() {
        taxService = new UkrainianTaxServiceImpl();
    }

    @Test
    public void testCalculateInteractiveBrokersTaxes() {
        BigDecimal income = new BigDecimal("10000.00");
        Map<UkrainianTaxCategory, BigDecimal> taxes = taxService.calculateInteractiveBrokersTaxes(income);

        // Test Personal Income Tax (18%)
        BigDecimal expectedIncomeTax = new BigDecimal("1800.00");
        assertEquals(taxes.get(UkrainianTaxCategory.INCOME_TAX), expectedIncomeTax, 
                    "Income tax calculation failed");

        // Test Military Tax (1.5%)
        BigDecimal expectedMilitaryTax = new BigDecimal("150.00");
        assertEquals(taxes.get(UkrainianTaxCategory.MILITARY_TAX), expectedMilitaryTax, 
                    "Military tax calculation failed");
    }

    @Test
    public void testCalculateTotalTax() {
        BigDecimal income = new BigDecimal("10000.00");
        BigDecimal expectedTotal = new BigDecimal("1950.00"); // 1800 (18%) + 150 (1.5%)
        
        BigDecimal actualTotal = taxService.calculateTotalTax(income);
        assertEquals(actualTotal, expectedTotal, "Total tax calculation failed");
    }

    @Test
    public void testGenerateTaxDeclaration() {
        BigDecimal income = new BigDecimal("10000.00");
        int year = 2024;
        
        Map<String, Object> declaration = taxService.generateTaxDeclaration(income, year);
        
        assertEquals(declaration.get("year"), year, "Year in declaration is incorrect");
        assertEquals(declaration.get("income"), income, "Income in declaration is incorrect");
        assertEquals(declaration.get("incomeTax"), new BigDecimal("1800.00"), 
                    "Income tax in declaration is incorrect");
        assertEquals(declaration.get("militaryTax"), new BigDecimal("150.00"), 
                    "Military tax in declaration is incorrect");
        assertEquals(declaration.get("totalTax"), new BigDecimal("1950.00"), 
                    "Total tax in declaration is incorrect");
        assertTrue(declaration.containsKey("generatedDate"), "Generated date is missing");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateTaxesWithNegativeIncome() {
        BigDecimal negativeIncome = new BigDecimal("-10000.00");
        taxService.calculateInteractiveBrokersTaxes(negativeIncome);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateTaxesWithNullIncome() {
        taxService.calculateInteractiveBrokersTaxes(null);
    }
} 