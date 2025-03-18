package com.ndewon.spendless.domain.usecase

import com.ndewon.spendless.domain.repository.TransactionRepository

class GetTransactionsUseCase(private val repo: TransactionRepository) {
    suspend fun invoke(username: String) = repo.getTransactions(username)
}