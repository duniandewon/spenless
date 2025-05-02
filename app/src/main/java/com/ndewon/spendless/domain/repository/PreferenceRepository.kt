package com.ndewon.spendless.domain.repository

import com.ndewon.spendless.domain.errors.DataError
import com.ndewon.spendless.domain.errors.Result
import com.ndewon.spendless.domain.models.Preference

interface PreferenceRepository {
    suspend fun createPreference(
        username: String,
        preference: Preference
    ): Result<Boolean, DataError>
    suspend fun getPreferences(username: String): Result<Preference, DataError>
    suspend fun updatePreferences(
        username: String,
        preference: Preference
    ): Result<Boolean, DataError>
}