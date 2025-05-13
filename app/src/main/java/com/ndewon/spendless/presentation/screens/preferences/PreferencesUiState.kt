package com.ndewon.spendless.presentation.screens.preferences

import com.ndewon.spendless.domain.models.Currency
import com.ndewon.spendless.domain.models.DecimalSeparator
import com.ndewon.spendless.domain.models.ExpenseFormat
import com.ndewon.spendless.domain.models.ThousandsSeparator

sealed class PreferencesUiState {
    object PreferenceSaved : PreferencesUiState()
    data class Preferences(
        val isLoading: Boolean = false,
        val errorMessage: String = "",
        val currencies: List<Currency> = emptyList(),
        val selectedFormat: ExpenseFormat = ExpenseFormat.PARENTHESES,
        val selectedCurrency: Currency = Currency(
            name = "Indonesian Rupiah",
            code = "IDR",
            symbol = "Rp"
        ),
        val selectedDecimalSeparator: DecimalSeparator = DecimalSeparator.DOT,
        val selectedThousandsSeparator: ThousandsSeparator = ThousandsSeparator.COMA
    ) : PreferencesUiState()
}