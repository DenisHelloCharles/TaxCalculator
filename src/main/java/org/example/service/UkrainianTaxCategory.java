package org.example.service;

import java.math.BigDecimal;

/**
 * Enum representing Ukrainian tax categories with their rates
 */
public enum UkrainianTaxCategory {
    INCOME_TAX(new BigDecimal("18.0")),          // Personal Income Tax (ПДФО)
    MILITARY_TAX(new BigDecimal("1.5")),         // Military Tax (Військовий збір)
    DIVIDEND_TAX(new BigDecimal("9.0")),         // Dividend Tax
    FOREIGN_INCOME_TAX(new BigDecimal("18.0"));  // Foreign Income Tax

    private final BigDecimal rate;

    UkrainianTaxCategory(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }
} 