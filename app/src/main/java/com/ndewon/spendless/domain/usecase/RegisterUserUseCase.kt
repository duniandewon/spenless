package com.ndewon.spendless.domain.usecase

import com.ndewon.spendless.domain.repository.UserRepository

class RegisterUserUseCase(private val userRepository: UserRepository) {
    suspend fun invoke(username: String, pin: String) {
        userRepository.registerUser(username, pin)
    }
}