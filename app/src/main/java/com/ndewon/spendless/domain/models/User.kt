package com.ndewon.spendless.domain.models

data class User(
    val username: String,
    val pinHash: String,
    val accountBalance: Double
)
