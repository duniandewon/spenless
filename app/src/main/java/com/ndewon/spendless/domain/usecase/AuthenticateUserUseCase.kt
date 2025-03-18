package com.ndewon.spendless.domain.usecase

import com.ndewon.spendless.domain.repository.UserRepository

class AuthenticateUserUseCase(private val repo: UserRepository) {
    suspend fun invoke(username: String, password: String) {
        repo.login(username, password)
    }
}