package com.ndewon.spendless.presentation.screens.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ndewon.spendless.domain.models.Category
import com.ndewon.spendless.domain.models.LargestTransaction
import com.ndewon.spendless.ui.theme.PaleYellowGreen

@Composable
fun TransactionsHighlight(
    modifier: Modifier = Modifier,
    mostPopularCategory: Category?,
    largestTransaction: LargestTransaction?,
    weeklyExpense: String
) {
    Column(modifier = modifier) {
        if (mostPopularCategory != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.12f)
                )
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = mostPopularCategory.name,
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "Most popular category",
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            LargestTransactionCard(
                largestTransaction = largestTransaction,
                modifier = Modifier.weight(2f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            WeaklyExpenseCard(weeklyExpense = weeklyExpense, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun LargestTransactionCard(modifier: Modifier = Modifier, largestTransaction: LargestTransaction?) {
    Card(modifier = modifier) {
        if (largestTransaction == null) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 2.dp),
                text = "Your largest transaction will appear here",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        } else {
            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 14.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = largestTransaction.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Largest transaction",
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "-$${largestTransaction.amount}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Jan 1, 2024",
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun WeaklyExpenseCard(modifier: Modifier = Modifier, weeklyExpense: String) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = PaleYellowGreen)
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 14.dp
            )
        ) {
            Text(
                text = weeklyExpense,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Previous week",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}