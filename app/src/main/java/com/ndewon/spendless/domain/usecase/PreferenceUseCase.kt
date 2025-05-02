package com.ndewon.spendless.domain.usecase

import com.ndewon.spendless.data.local.dao.PreferenceDao
import com.ndewon.spendless.data.mapper.PreferenceDataMapper
import com.ndewon.spendless.domain.errors.DataError
import com.ndewon.spendless.domain.errors.Result
import com.ndewon.spendless.domain.models.Preference
import com.ndewon.spendless.domain.repository.PreferenceRepository

class PreferenceUseCase(
    private val preferenceDao: PreferenceDao,
    val mapper: PreferenceDataMapper
) : PreferenceRepository {
    override suspend fun createPreference(
        username: String,
        preference: Preference
    ): Result<Boolean, DataError> {
        return try {
            preferenceDao.insertPreference(mapper.preferenceDomainToEntity(preference))
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(DataError.LocalError.UNKNOWN)
        }
    }

    override suspend fun getPreferences(username: String): Result<Preference, DataError> {
        return try {
            val entity = preferenceDao.getPreferences()
            if (entity != null) {
                Result.Success(mapper.preferenceEntityToDomain(entity))
            } else {
                Result.Error(DataError.LocalError.PREFERENCE_NOT_FOUND)
            }
        } catch (e: Exception) {
            Result.Error(DataError.LocalError.UNKNOWN)
        }
    }

    override suspend fun updatePreferences(
        username: String,
        preference: Preference
    ): Result<Boolean, DataError> {
        return try {
            val entity = mapper.preferenceDomainToEntity(preference)
            preferenceDao.updatePreferences(
                entity.id,
                entity.currency,
                entity.expenseFormat,
                entity.decimalSeparator,
                entity.thousandSeparator
            )
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(DataError.LocalError.UNKNOWN)
        }
    }
}