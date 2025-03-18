package com.ndewon.spendless.domain.repository

import com.ndewon.spendless.domain.models.Preference

interface PreferenceRepository {
    suspend fun getPreferences(username: String): Preference
    suspend fun updatePreferences(username: String, preference: Preference)
}