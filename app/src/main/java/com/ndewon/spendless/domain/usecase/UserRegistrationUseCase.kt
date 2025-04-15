package com.ndewon.spendless.domain.usecase

import com.ndewon.spendless.data.local.dao.UserDao
import com.ndewon.spendless.data.mapper.UserDataMapper
import com.ndewon.spendless.domain.models.User
import com.ndewon.spendless.domain.repository.UserRepository

class UserRegistrationUseCase(private val userDao: UserDao) : UserRepository {
    override suspend fun createUser(username: String): User {
        val user = getUser(username)

        if (user != null) {
            throw IllegalArgumentException("User already exists")
        }

        val id = userDao.insertUser(UserDataMapper().userDomainToEntity(User(0, username)))

        val insertedUser = userDao.getUserById(id)!!

        return UserDataMapper().userEntityToDomain(insertedUser)
    }

    override suspend fun createPin(
        username: String,
        pin: String
    ): Boolean {
        val user = getUser(username) ?: throw IllegalArgumentException("User not found")

        // TODO: Hash pin first
        userDao.updatePin(username, pin)

        return true
    }

    override suspend fun getUser(username: String): User? {
        val user = userDao.getUserByUsername(username)
        return user?.let {
            UserDataMapper().userEntityToDomain(it)
        }
    }

    override suspend fun removeUser(username: String): Boolean {
        val user = getUser(username) ?: throw IllegalArgumentException("User not found")

        return userDao.deleteUser(user.id) > 0
    }
}