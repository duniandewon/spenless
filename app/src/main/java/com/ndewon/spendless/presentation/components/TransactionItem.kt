package com.ndewon.spendless.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.ndewon.spendless.domain.models.Transaction
import com.ndewon.spendless.domain.models.TransactionType

@Composable
fun TransactionItem(modifier: Modifier = Modifier, transaction: Transaction) {
    val expanded = remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {
            if (!transaction.description.isNullOrBlank()) expanded.value = !expanded.value
        }
    ) {
        /*TODO: Add icon for transaction type*/
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = transaction.title,
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 16.sp)
                    )
                    Text(
                        text = transaction.category.name,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp)
                    )
                }

                Text(
                    text = if (transaction.type == TransactionType.EXPENSE) "-${transaction.amount}" else "${transaction.amount}",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            if (expanded.value) {
                Text(
                    text = transaction.description ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}