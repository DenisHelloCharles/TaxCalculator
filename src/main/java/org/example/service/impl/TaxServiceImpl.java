package org.example.service.impl;

import org.example.service.TaxService;
import org.example.service.TaxCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Implementation of TaxService for calculating various types of taxes
 */
public class TaxServiceImpl implements TaxService {
    private static final Logger logger = LoggerFactory.getLogger(TaxServiceImpl.class);
    private static final int DECIMAL_PLACES = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    @Override
    public BigDecimal calculateTax(BigDecimal amount, BigDecimal taxRate) {
        if (amount == null || taxRate == null) {
            logger.error("Amount or tax rate is null");
            throw new IllegalArgumentException("Amount and tax rate must not be null");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            logger.error("Amount is negative: {}", amount);
            throw new IllegalArgumentException("Amount must not be negative");
        }

        if (taxRate.compareTo(BigDecimal.ZERO) < 0) {
            logger.error("Tax rate is negative: {}", taxRate);
            throw new IllegalArgumentException("Tax rate must not be negative");
        }

        logger.debug("Calculating tax for amount: {} with rate: {}", amount, taxRate);
        BigDecimal tax = amount.multiply(taxRate).divide(new BigDecimal("100"), DECIMAL_PLACES, ROUNDING_MODE);
        logger.info("Calculated tax: {} for amount: {} with rate: {}", tax, amount, taxRate);
        return tax;
    }

    @Override
    public BigDecimal calculateTotalWithTax(BigDecimal amount, BigDecimal taxRate) {
        BigDecimal tax = calculateTax(amount, taxRate);
        BigDecimal total = amount.add(tax);
        logger.info("Calculated total with tax: {} (base: {}, tax: {})", total, amount, tax);
        return total;
    }

    @Override
    public BigDecimal calculateTaxByCategory(BigDecimal amount, TaxCategory category) {
        if (category == null) {
            logger.error("Tax category is null");
            throw new IllegalArgumentException("Tax category must not be null");
        }

        logger.debug("Calculating tax for amount: {} with category: {}", amount, category);
        return calculateTax(amount, category.getRate());
    }
} 