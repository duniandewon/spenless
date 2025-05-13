package com.ndewon.spendless.presentation.screens.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndewon.spendless.domain.errors.Result
import com.ndewon.spendless.domain.models.Preference
import com.ndewon.spendless.domain.repository.CurrencyRepository
import com.ndewon.spendless.domain.repository.PreferenceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PreferencesViewModel(
    private val preferenceRepository: PreferenceRepository,
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PreferencesUiState>(PreferencesUiState.Preferences())
    val uiState: StateFlow<PreferencesUiState> = _uiState.asStateFlow()

    init {
        updateState { it.copy(currencies = currencyRepository.getCurrencies()) }
    }

    fun updateState(update: (PreferencesUiState.Preferences) -> PreferencesUiState.Preferences) {
        _uiState.value = (_uiState.value as PreferencesUiState.Preferences).let(update)
    }

    fun onEvent(event: PreferenceUiEvent) {
        when (event) {
            PreferenceUiEvent.PreferenceSaved -> {}
            is PreferenceUiEvent.SelectedCurrencyChanged -> {
                updateState { it.copy(selectedCurrency = event.currency) }
            }

            is PreferenceUiEvent.SelectedDecimalSeparatorChanged -> {
                updateState { it.copy(selectedDecimalSeparator = event.separator) }
            }

            is PreferenceUiEvent.SelectedFormatChanged -> {
                updateState { it.copy(selectedFormat = event.format) }
            }

            is PreferenceUiEvent.SelectedThousandsSeparatorChanged -> {
                updateState { it.copy(selectedThousandsSeparator = event.separator) }
            }

            is PreferenceUiEvent.SavePreference -> {
                setPreferences(event.preference)
            }
        }
    }

    fun setPreferences(preference: Preference) {
        viewModelScope.launch() {
            when (val resul = preferenceRepository.createPreference("username", preference)) {
                is Result.Success -> {
                    _uiState.value = PreferencesUiState.PreferenceSaved
                }
            }
        }
    }
}