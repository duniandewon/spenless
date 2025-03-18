package com.ndewon.spendless.domain.models

import java.util.Date

data class Transaction(
    val id: String,
    val title: String,
    val amount: Double,
    val type: TransactionType,
    val category: Category,
    val description: String?,
    val date: Date
)
