package com.ndewon.spendless.domain.repository

import com.ndewon.spendless.domain.models.User

interface UserRepository {
    suspend fun registerUser(username: String, pinHash: String): User
    suspend fun login(username: String, pin: String): Boolean
    suspend fun getUser(username: String): User?
    suspend fun updateAccountBalance(username: String, amount: Double)
}