package com.ndewon.spendless.presentation.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ndewon.spendless.domain.models.DecimalSeparator
import com.ndewon.spendless.domain.models.ExpenseFormat
import com.ndewon.spendless.domain.models.ThousandsSeparator
import com.ndewon.spendless.presentation.screens.dashboard.components.DashboardHeader
import com.ndewon.spendless.presentation.screens.dashboard.components.ExpenseDisplay
import com.ndewon.spendless.presentation.screens.dashboard.components.TransactionsHighlight
import com.ndewon.spendless.presentation.screens.dashboard.components.TransactionsListContainer
import com.ndewon.spendless.utils.formatNumber
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = koinViewModel()) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        is DashboardUiState.Dashboard -> {
            Dashboard(uiState = uiState, onEvent = viewModel::onEvent)
        }
    }
}

@Composable
fun Dashboard(
    uiState: DashboardUiState.Dashboard, onEvent: (DashboardUiEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    center = Offset(0.2136f, 0.1166f),
                    radius = 1000f,
                )
            )

    ) {
        Column(
            modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            DashboardHeader(onClickSettings = { onEvent(DashboardUiEvent.OpenSettings) })

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                ExpenseDisplay(
                    formatNumber(
                        number = 1234567.89,
                        thousandsSeparator = ThousandsSeparator.COMA,
                        decimalSeparator = DecimalSeparator.DOT,
                        format = ExpenseFormat.NEGATIVE_SIGN,
                        currencySymbol = "IDR"
                    )
                )

                Spacer(modifier = Modifier.height(46.dp))

                TransactionsHighlight(
                    mostPopularCategory = uiState.mostPopularCategory,
                    largestTransaction = uiState.largestTransaction,
                    weeklyExpense = uiState.weeklyExpense
                )
            }

            TransactionsListContainer(
                modifier = Modifier.weight(1f), transactionsPerDay = uiState.transactions
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    Dashboard(uiState = DashboardUiState.Dashboard(), onEvent = {})
}