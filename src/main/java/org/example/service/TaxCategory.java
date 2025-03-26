package org.example.service;

import java.math.BigDecimal;

/**
 * Enum representing different tax categories with their rates
 */
public enum TaxCategory {
    STANDARD(new BigDecimal("20.0")),    // Standard VAT rate
    REDUCED(new BigDecimal("5.0")),      // Reduced rate for essential goods
    ZERO(BigDecimal.ZERO),               // Zero rate for exempt items
    LUXURY(new BigDecimal("25.0"));      // Higher rate for luxury items

    private final BigDecimal rate;

    TaxCategory(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }
} 