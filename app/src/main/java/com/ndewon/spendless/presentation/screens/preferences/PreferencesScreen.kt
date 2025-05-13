package com.ndewon.spendless.presentation.screens.preferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import com.ndewon.spendless.domain.models.Preference
import com.ndewon.spendless.domain.models.DecimalSeparator
import com.ndewon.spendless.domain.models.ExpenseFormat
import com.ndewon.spendless.domain.models.ThousandsSeparator
import com.ndewon.spendless.presentation.components.DropDownOptions
import com.ndewon.spendless.presentation.components.PreferenceDisplay
import com.ndewon.spendless.presentation.components.SegmentedButton
import com.ndewon.spendless.utils.formatNumber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferencesScreen(
    onBackClick: () -> Unit,
    onPreferencesSaved: () -> Unit,
    viewModel: PreferencesViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        PreferencesUiState.PreferenceSaved -> {
            onPreferencesSaved()
        }

        is PreferencesUiState.Preferences -> {
            PreferencesComponent(
                onBackClick = onBackClick,
                uiState = uiState,
                onEvent = viewModel::onEvent
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferencesComponent(
    onBackClick: () -> Unit,
    uiState: PreferencesUiState.Preferences,
    onEvent: (PreferenceUiEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {}, navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            })
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Column {
                    Text(
                        text = "Set SpendLess\nto your preferences",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "You can change it at any time in Settings"
                    )
                }

                Spacer(Modifier.height(24.dp))

                PreferenceDisplay(
                    amount = formatNumber(
                        number = 1234567.89,
                        thousandsSeparator = uiState.selectedThousandsSeparator,
                        decimalSeparator = uiState.selectedDecimalSeparator,
                        format = uiState.selectedFormat,
                        currencySymbol = uiState.selectedCurrency.symbol
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Column {
                    Text(text = "Expense format", style = MaterialTheme.typography.labelMedium)
                    Spacer(Modifier.height(4.dp))
                    SegmentedButton(
                        options = ExpenseFormat.entries.toList(),
                        selectedOption = uiState.selectedFormat,
                        onFormatSelected = {
                            onEvent(PreferenceUiEvent.SelectedFormatChanged(it))
                        },
                        label = { format ->
                            when (format) {
                                ExpenseFormat.NEGATIVE_SIGN -> "-${uiState.selectedCurrency.symbol}10"
                                ExpenseFormat.PARENTHESES -> "(${uiState.selectedCurrency.symbol}10)"
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column {
                    Text(text = "Currency", style = MaterialTheme.typography.labelMedium)
                    Spacer(Modifier.height(4.dp))
                    DropDownOptions(
                        options = uiState.currencies,
                        selectedOption = uiState.selectedCurrency,
                        onOptionSelected = {
                            onEvent(PreferenceUiEvent.SelectedCurrencyChanged(it))
                        },
                        optionItem = { currency ->
                            Row {
                                Text(text = currency.symbol)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "${currency.name} (${currency.code})")
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column {
                    Text(text = "Decimal separator", style = MaterialTheme.typography.labelMedium)
                    Spacer(Modifier.height(4.dp))
                    SegmentedButton(
                        options = DecimalSeparator.entries.toList(),
                        selectedOption = uiState.selectedDecimalSeparator,
                        onFormatSelected = {
                            onEvent(PreferenceUiEvent.SelectedDecimalSeparatorChanged(it))
                        },
                        label = { separator ->
                            if (separator == DecimalSeparator.DOT) "1.00" else "1,00"
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column {
                    Text(text = "Thousands separator", style = MaterialTheme.typography.labelMedium)
                    Spacer(Modifier.height(4.dp))
                    SegmentedButton(
                        options = ThousandsSeparator.entries.toList(),
                        selectedOption = uiState.selectedThousandsSeparator,
                        onFormatSelected = {
                            onEvent(PreferenceUiEvent.SelectedThousandsSeparatorChanged(it))
                        },
                        label = { separator ->
                            when (separator) {
                                ThousandsSeparator.DOT -> "1.000"
                                ThousandsSeparator.COMA -> "1,000"
                                ThousandsSeparator.SPACE -> "1 000"
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val savedPreferences = Preference(
                            currency = uiState.selectedCurrency.code,
                            expenseFormat = uiState.selectedFormat,
                            decimalSeparator = uiState.selectedDecimalSeparator,
                            thousandSeparator = uiState.selectedThousandsSeparator
                        )
                        onEvent(PreferenceUiEvent.SavePreference(savedPreferences))
                    },
                ) {
                    Text(
                        text = "Start tracking!"
                    )
                }
            }
        }
    }
}