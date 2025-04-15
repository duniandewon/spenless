package com.ndewon.spendless.domain.models

data class User(
    val id: Long = 0,
    val username: String,
    val pinHash: String? = null,
    val accountBalance: Double = 0.0
)
