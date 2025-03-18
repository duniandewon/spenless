package com.ndewon.spendless.domain.usecase

import com.ndewon.spendless.domain.models.Transaction
import com.ndewon.spendless.domain.repository.TransactionRepository

class CreateTransactionUseCase(val repo: TransactionRepository) {
    suspend fun invoke(transaction: Transaction) = repo.addTransaction(transaction)
}