# Ukrainian Tax Calculator

A Java service for calculating Ukrainian taxes, specifically designed for Interactive Brokers income declarations. This service helps calculate personal income tax (ПДФО) and military tax (Військовий збір) based on your income.

## Features

- Calculate taxes for Interactive Brokers income:
  - Personal Income Tax (ПДФО) - 18%
  - Military Tax (Військовий збір) - 1.5%
  - Dividend Tax - 9%
  - Foreign Income Tax - 18%
- Generate tax declarations with all required information
- Precise decimal calculations using BigDecimal
- Comprehensive logging
- Input validation and error handling
- Full test coverage

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Installation

1. Clone the repository:
```bash
git clone [repository-url]
cd [project-directory]
```

2. Build the project:
```bash
mvn clean install
```

## Usage

### Basic Usage

```java
// Create tax service instance
UkrainianTaxService taxService = new UkrainianTaxServiceImpl();

// Calculate taxes for income
BigDecimal income = new BigDecimal("10000.00");
Map<UkrainianTaxCategory, BigDecimal> taxes = taxService.calculateInteractiveBrokersTaxes(income);

// Get individual tax amounts
BigDecimal incomeTax = taxes.get(UkrainianTaxCategory.INCOME_TAX);      // 1800.00 (18%)
BigDecimal militaryTax = taxes.get(UkrainianTaxCategory.MILITARY_TAX);  // 150.00 (1.5%)

// Calculate total tax
BigDecimal totalTax = taxService.calculateTotalTax(income);  // 1950.00
```

### Generating Tax Declaration

```java
// Generate tax declaration for specific year
Map<String, Object> declaration = taxService.generateTaxDeclaration(income, 2024);

// Declaration contains:
// - year: 2024
// - income: 10000.00
// - incomeTax: 1800.00
// - militaryTax: 150.00
// - totalTax: 1950.00
// - generatedDate: current date
```

## Tax Categories

The service supports the following tax categories:

| Category | Rate | Description |
|----------|------|-------------|
| INCOME_TAX | 18.0% | Personal Income Tax (ПДФО) |
| MILITARY_TAX | 1.5% | Military Tax (Військовий збір) |
| DIVIDEND_TAX | 9.0% | Dividend Tax |
| FOREIGN_INCOME_TAX | 18.0% | Foreign Income Tax |

## Configuration

The service uses the following default configuration:

- Decimal places: 2
- Rounding mode: HALF_UP
- Logging: SLF4J with Logback

## Testing

Run the tests using:
```bash
mvn test
```

The test suite includes:
- Basic tax calculations
- Total tax calculations
- Tax declaration generation
- Input validation
- Error handling

## Logging

The service uses SLF4J with Logback for logging. Log messages include:
- Tax calculation details
- Input validation errors
- Declaration generation events

## Error Handling

The service includes validation for:
- Null income values
- Negative income values
- Invalid tax categories

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Based on the functionality from [ua-tax.web.app](https://ua-tax.web.app/faq)
- Uses TestNG for testing
- Implements precise decimal calculations using BigDecimal 