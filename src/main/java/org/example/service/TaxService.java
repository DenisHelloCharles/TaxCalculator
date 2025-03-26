package org.example.service;

import java.math.BigDecimal;

/**
 * Service interface for tax calculations
 */
public interface TaxService {
    /**
     * Calculate tax amount based on the base amount and tax rate
     * @param amount The base amount to calculate tax on
     * @param taxRate The tax rate as a percentage (e.g., 20.0 for 20%)
     * @return The calculated tax amount
     */
    BigDecimal calculateTax(BigDecimal amount, BigDecimal taxRate);

    /**
     * Calculate total amount including tax
     * @param amount The base amount
     * @param taxRate The tax rate as a percentage
     * @return The total amount including tax
     */
    BigDecimal calculateTotalWithTax(BigDecimal amount, BigDecimal taxRate);

    /**
     * Calculate tax amount for a specific tax category
     * @param amount The base amount
     * @param category The tax category
     * @return The calculated tax amount
     */
    BigDecimal calculateTaxByCategory(BigDecimal amount, TaxCategory category);
} 