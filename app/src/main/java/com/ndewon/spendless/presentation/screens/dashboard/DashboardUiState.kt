package com.ndewon.spendless.presentation.screens.dashboard

import com.ndewon.spendless.domain.models.Category
import com.ndewon.spendless.domain.models.LargestTransaction
import com.ndewon.spendless.domain.models.Transaction
import java.util.Date

sealed class DashboardUiState {
    data class Dashboard(
        val isLoading: Boolean = true,
        val errorMessage: String = "",
        val transactions: Map<Date, List<Transaction>> = emptyMap(),
        val mostPopularCategory: Category? = null,
        val largestTransaction: LargestTransaction? = null,
        val weeklyExpense: String = "0"
    ) :
        DashboardUiState()
}