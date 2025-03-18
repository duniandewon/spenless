package com.ndewon.spendless.domain.models

data class Preference(
    val currency: String,
    val expenseFormat: ExpenseFormat, // -$10 or ($10)
    val decimalSeparator: Char, // '.' or ','
    val thousandSeparator: String // "1.000", "1,000", or "1 000"
)