package org.example.service.impl;

import org.example.service.UkrainianTaxService;
import org.example.service.UkrainianTaxCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;

/**
 * Implementation of UkrainianTaxService for calculating taxes based on Interactive Brokers data
 */
public class UkrainianTaxServiceImpl implements UkrainianTaxService {
    private static final Logger logger = LoggerFactory.getLogger(UkrainianTaxServiceImpl.class);
    private static final int DECIMAL_PLACES = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    @Override
    public Map<UkrainianTaxCategory, BigDecimal> calculateInteractiveBrokersTaxes(BigDecimal income) {
        if (income == null) {
            logger.error("Income amount is null");
            throw new IllegalArgumentException("Income amount must not be null");
        }

        if (income.compareTo(BigDecimal.ZERO) < 0) {
            logger.error("Income amount is negative: {}", income);
            throw new IllegalArgumentException("Income amount must not be negative");
        }

        Map<UkrainianTaxCategory, BigDecimal> taxes = new HashMap<>();
        
        // Calculate Personal Income Tax (18%)
        BigDecimal incomeTax = calculateTax(income, UkrainianTaxCategory.INCOME_TAX.getRate());
        taxes.put(UkrainianTaxCategory.INCOME_TAX, incomeTax);
        
        // Calculate Military Tax (1.5%)
        BigDecimal militaryTax = calculateTax(income, UkrainianTaxCategory.MILITARY_TAX.getRate());
        taxes.put(UkrainianTaxCategory.MILITARY_TAX, militaryTax);

        logger.info("Calculated taxes for income {}: Income Tax = {}, Military Tax = {}", 
                   income, incomeTax, militaryTax);
        
        return taxes;
    }

    @Override
    public BigDecimal calculateTotalTax(BigDecimal income) {
        Map<UkrainianTaxCategory, BigDecimal> taxes = calculateInteractiveBrokersTaxes(income);
        BigDecimal totalTax = taxes.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        logger.info("Calculated total tax for income {}: {}", income, totalTax);
        return totalTax;
    }

    @Override
    public Map<String, Object> generateTaxDeclaration(BigDecimal income, int year) {
        Map<String, Object> declaration = new HashMap<>();
        Map<UkrainianTaxCategory, BigDecimal> taxes = calculateInteractiveBrokersTaxes(income);

        declaration.put("year", year);
        declaration.put("income", income);
        declaration.put("incomeTax", taxes.get(UkrainianTaxCategory.INCOME_TAX));
        declaration.put("militaryTax", taxes.get(UkrainianTaxCategory.MILITARY_TAX));
        declaration.put("totalTax", calculateTotalTax(income));
        declaration.put("generatedDate", LocalDate.now());

        logger.info("Generated tax declaration for year {} with income {}", year, income);
        return declaration;
    }

    private BigDecimal calculateTax(BigDecimal amount, BigDecimal taxRate) {
        return amount.multiply(taxRate)
                    .divide(new BigDecimal("100"), DECIMAL_PLACES, ROUNDING_MODE);
    }
} 