package com.ndewon.spendless.domain.repository

import com.ndewon.spendless.domain.models.SecuritySetting

interface SecurityRepository {
    suspend fun getSecuritySettings(username: String): SecuritySetting
    suspend fun updateSecuritySettings(username: String, securitySetting: SecuritySetting)
}