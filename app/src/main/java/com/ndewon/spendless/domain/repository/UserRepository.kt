package com.ndewon.spendless.domain.repository

import com.ndewon.spendless.domain.models.User

interface UserRepository {
    suspend fun createUser(username: String): User
    suspend fun createPin(username: String, pin: String): Boolean
    suspend fun getUser(username: String): User?
    suspend fun removeUser(username: String): Boolean
}