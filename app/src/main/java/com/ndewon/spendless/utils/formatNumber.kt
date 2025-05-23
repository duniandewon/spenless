package com.ndewon.spendless.utils

import com.ndewon.spendless.domain.models.DecimalSeparator
import com.ndewon.spendless.domain.models.ExpenseFormat
import com.ndewon.spendless.domain.models.ThousandsSeparator
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun formatNumber(
    number: Double,
    thousandsSeparator: ThousandsSeparator,
    decimalSeparator: DecimalSeparator,
    format: ExpenseFormat,
    currencySymbol: String
): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
        groupingSeparator = when (thousandsSeparator) {
            ThousandsSeparator.DOT -> '.'
            ThousandsSeparator.COMA -> ','
            ThousandsSeparator.SPACE -> ' '
        }
        this.decimalSeparator = when (decimalSeparator) {
            DecimalSeparator.DOT -> '.'
            DecimalSeparator.COMA -> ','
        }
    }
    val pattern = "#,##0.###"
    val formatter = DecimalFormat(pattern, symbols)
    val formattedNumber = formatter.format(number)

    val formattedWithCurrency = currencySymbol + formattedNumber

    return when (format) {
        ExpenseFormat.NEGATIVE_SIGN -> "-$formattedWithCurrency"
        ExpenseFormat.PARENTHESES -> "($formattedWithCurrency)"
    }
}