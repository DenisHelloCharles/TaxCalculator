package org.example.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.HashMap;

/**
 * Service interface for Ukrainian tax calculations
 */
public interface UkrainianTaxService {
    /**
     * Calculate tax for Interactive Brokers income
     * @param income The income amount
     * @return Map containing calculated taxes by category
     */
    Map<UkrainianTaxCategory, BigDecimal> calculateInteractiveBrokersTaxes(BigDecimal income);

    /**
     * Calculate total tax amount for all categories
     * @param income The income amount
     * @return Total tax amount
     */
    BigDecimal calculateTotalTax(BigDecimal income);

    /**
     * Generate tax declaration data
     * @param income The income amount
     * @param year The tax year
     * @return Map containing declaration data
     */
    Map<String, Object> generateTaxDeclaration(BigDecimal income, int year);
} 