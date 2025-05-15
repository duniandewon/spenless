package com.ndewon.spendless.domain.usecase

import com.ndewon.spendless.data.local.dao.UserDao
import com.ndewon.spendless.data.mapper.UserDataMapper
import com.ndewon.spendless.domain.errors.DataError
import com.ndewon.spendless.domain.errors.Result
import com.ndewon.spendless.domain.models.User
import com.ndewon.spendless.domain.repository.UserRepository

class UserRegistrationUseCase(private val userDao: UserDao, val mapper: UserDataMapper) :
    UserRepository {
    override suspend fun createUser(username: String, pin: String): Result<User, DataError> {
        return when (getUserByUsername(username)) {
            is Result.Success -> {
                Result.Error(DataError.LocalError.USER_ALREADY_EXISTS)
            }

            is Result.Error -> {
                val id = userDao.insertUser(mapper.userDomainToEntity(User(0, username, pin)))

                Result.Success(User(id, username, pin))
            }

            else -> {
                Result.Error(DataError.LocalError.UNKNOWN)
            }
        }
    }

    override suspend fun getUserByUsername(username: String): Result<User?, DataError> {
        val user = userDao.getUserByUsername(username)
        return if (user == null) {
            Result.Error(DataError.LocalError.USER_NOT_FOUND)
        } else {
            Result.Success(mapper.userEntityToDomain(user))
        }
    }

    override suspend fun removeUser(username: String): Result<Boolean, DataError> {
        return when (val userResult = getUserByUsername(username)) {
            is Result.Success -> {
                val user = userResult.data
                    ?: return Result.Error(DataError.LocalError.USER_NOT_FOUND)

                val deleted = userDao.deleteUser(user.id)
                Result.Success(deleted > 0)
            }

            is Result.Error -> userResult

            else -> {
                Result.Error(DataError.LocalError.UNKNOWN)
            }
        }
    }
}