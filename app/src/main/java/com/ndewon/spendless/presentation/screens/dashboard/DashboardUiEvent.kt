package com.ndewon.spendless.presentation.screens.dashboard

sealed class DashboardUiEvent {
    data object OpenSettings : DashboardUiEvent()
}