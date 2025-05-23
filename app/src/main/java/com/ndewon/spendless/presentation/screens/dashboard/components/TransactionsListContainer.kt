package com.ndewon.spendless.presentation.screens.dashboard.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ndewon.spendless.domain.models.Transaction
import com.ndewon.spendless.presentation.components.TransactionItem
import java.util.Date
import kotlin.collections.forEach

@Composable
fun TransactionsListContainer(
    modifier: Modifier = Modifier, transactionsPerDay: Map<Date, List<Transaction>> = emptyMap()
) {
    Surface(
        shape = RoundedCornerShape(16.dp), modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        if (transactionsPerDay.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No transactions yet", style = MaterialTheme.typography.titleLarge)
            }
        } else {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp)
                ) {
                    Text(
                        text = "Latest Transactions",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.weight(1f)
                    )

                    TextButton(
                        onClick = { /*TODO*/ }, colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary,
                        )
                    ) {
                        Text(
                            text = "See all",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                LazyColumn {
                    items(transactionsPerDay.size) { index ->
                        val date = transactionsPerDay.keys.elementAt(index)
                        val transactions = transactionsPerDay[date] ?: emptyList()

                        TransactionsPerDay(date = date, transactions = transactions)
                    }
                }
            }
        }
    }
}

@Composable
fun TransactionsPerDay(date: Date, transactions: List<Transaction>) {
    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Column {
            Text(
                text = "${date.day} ${date.month} ${date.year}",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp
            )

            transactions.forEach { transaction ->
                TransactionItem(transaction = transaction)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}