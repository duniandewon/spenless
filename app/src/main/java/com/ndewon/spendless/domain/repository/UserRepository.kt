package com.ndewon.spendless.domain.repository

import com.ndewon.spendless.domain.errors.DataError
import com.ndewon.spendless.domain.errors.Result
import com.ndewon.spendless.domain.models.User

interface UserRepository {
    suspend fun createUser(username: String): Result<User, DataError>
    suspend fun createPin(username: String, pin: String): Result<Boolean, DataError>
    suspend fun getUser(username: String): Result<User?, DataError>
    suspend fun removeUser(username: String): Result<Boolean, DataError>
}