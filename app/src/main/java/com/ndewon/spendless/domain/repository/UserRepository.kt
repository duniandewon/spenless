package com.ndewon.spendless.domain.repository

import com.ndewon.spendless.domain.errors.DataError
import com.ndewon.spendless.domain.errors.Result
import com.ndewon.spendless.domain.models.User

interface UserRepository {
    suspend fun createUser(username: String, pin: String): Result<User, DataError>
    suspend fun getUserByUsername(username: String): Result<User?, DataError>
    suspend fun removeUser(username: String): Result<Boolean, DataError>
}