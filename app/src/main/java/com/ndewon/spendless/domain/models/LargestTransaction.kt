package com.ndewon.spendless.domain.models

import java.util.Date

data class LargestTransaction(
    val id: String,
    val title: String,
    val amount: String,
    val type: TransactionType,
    val date: Date,
)
