package com.ndewon.spendless.presentation.screens.preferences

import com.ndewon.spendless.domain.models.Currency
import com.ndewon.spendless.domain.models.DecimalSeparator
import com.ndewon.spendless.domain.models.ExpenseFormat
import com.ndewon.spendless.domain.models.Preference
import com.ndewon.spendless.domain.models.ThousandsSeparator

sealed class PreferenceUiEvent {
    data class SavePreference(val preference: Preference) : PreferenceUiEvent()
    data object PreferenceSaved : PreferenceUiEvent()
    data class SelectedFormatChanged(val format: ExpenseFormat) : PreferenceUiEvent()
    data class SelectedCurrencyChanged(val currency: Currency) : PreferenceUiEvent()
    data class SelectedDecimalSeparatorChanged(val separator: DecimalSeparator) :
        PreferenceUiEvent()

    data class SelectedThousandsSeparatorChanged(val separator: ThousandsSeparator) :
        PreferenceUiEvent()
}