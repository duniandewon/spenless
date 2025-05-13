package com.ndewon.spendless.presentation.screens.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndewon.spendless.domain.errors.Result
import com.ndewon.spendless.domain.models.DecimalSeparator
import com.ndewon.spendless.domain.models.ExpenseFormat
import com.ndewon.spendless.domain.models.Preference
import com.ndewon.spendless.domain.repository.PreferenceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PreferencesViewModel(private val preferenceRepository: PreferenceRepository) : ViewModel() {
    private val _saveResult = MutableStateFlow<String?>(null)
    val saveResult: StateFlow<String?> = _saveResult

    fun saveTestPreference() {
        viewModelScope.launch() {
            val testPreference = Preference(
                currency = "USD",
                expenseFormat = ExpenseFormat.NEGATIVE_SIGN,
                decimalSeparator = DecimalSeparator.DOT,
                thousandSeparator = DecimalSeparator.COMA
            )

            when (val result = preferenceRepository.createPreference("testUser", testPreference)) {
                is Result.Success -> _saveResult.value = "Preference saved successfully!"
                is Result.Error -> _saveResult.value = "Failed to save preference: ${result.error}"
            }
        }
    }
}