package com.ndewon.spendless.domain.models

data class Preference(
    val id: Long = 0,
    val currency: String,
    val expenseFormat: ExpenseFormat, // -$10 or ($10)
    val decimalSeparator: DecimalSeparator, // '.' or ','
    val thousandSeparator: ThousandsSeparator // "1.000", "1,000", or "1 000"
)