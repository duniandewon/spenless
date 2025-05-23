package com.ndewon.spendless.presentation.screens.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow<DashboardUiState>(
        DashboardUiState.Dashboard()
    )
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: DashboardUiEvent) {
        when (event) {

            else -> {
                Log.d("DashboardViewModel", "Unknown event: $event")
            }
        }
    }
}