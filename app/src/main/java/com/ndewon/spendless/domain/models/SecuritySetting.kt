package com.ndewon.spendless.domain.models

data class SecuritySetting(
    val sessionExpiry: Long,
    val lockoutDuration: Long
)