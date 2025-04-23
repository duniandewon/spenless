package com.ndewon.spendless.domain.usecase

import com.ndewon.spendless.data.local.dao.UserDao
import com.ndewon.spendless.data.mapper.UserDataMapper
import com.ndewon.spendless.domain.errors.DataError
import com.ndewon.spendless.domain.errors.Result
import com.ndewon.spendless.domain.models.User
import com.ndewon.spendless.domain.repository.UserRepository

class UserRegistrationUseCase(private val userDao: UserDao, val mapper: UserDataMapper) :
    UserRepository {
    override suspend fun createUser(username: String): Result<User, DataError> {
        return when (val existingUserResult = getUser(username)) {
            is Result.Success -> {
                if (existingUserResult.data != null) {
                    return Result.Error(DataError.LocalError.USER_ALREADY_EXISTS)
                }

                val id = userDao.insertUser(mapper.userDomainToEntity(User(0, username)))
                val insertedUser = userDao.getUserById(id)
                    ?: return Result.Error(DataError.LocalError.UNKNOWN)

                Result.Success(mapper.userEntityToDomain(insertedUser))
            }

            is Result.Error -> existingUserResult
            else -> {
                Result.Error(DataError.LocalError.UNKNOWN)
            }
        }
    }

    override suspend fun createPin(username: String, pin: String): Result<Boolean, DataError> {
        return when (val userResult = getUser(username)) {
            is Result.Success -> {
                if (userResult.data == null) {
                    return Result.Error(DataError.LocalError.USER_NOT_FOUND)
                }

                userDao.updatePin(username, pin)
                Result.Success(true)
            }

            is Result.Error -> userResult

            else -> {
                Result.Error(DataError.LocalError.UNKNOWN)
            }
        }
    }

    override suspend fun getUser(username: String): Result<User?, DataError> {
        return when (val user = userDao.getUserByUsername(username)) {
            null -> Result.Error(DataError.LocalError.USER_NOT_FOUND)
            else -> {
                Result.Success(user.let { mapper.userEntityToDomain(user) })
            }
        }
    }

    override suspend fun removeUser(username: String): Result<Boolean, DataError> {
        return when (val userResult = getUser(username)) {
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