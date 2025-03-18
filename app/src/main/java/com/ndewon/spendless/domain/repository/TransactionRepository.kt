package com.ndewon.spendless.domain.repository

import com.ndewon.spendless.domain.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun addTransaction(transaction: Transaction)
    suspend fun getTransactions(username: String): Flow<Transaction>
}