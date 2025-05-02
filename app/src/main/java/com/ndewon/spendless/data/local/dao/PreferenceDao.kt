package com.ndewon.spendless.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ndewon.spendless.data.local.entity.PreferenceEntity

@Dao
interface PreferenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreference(preference: PreferenceEntity): Long

    @Query("SELECT * FROM preferences")
    suspend fun getPreferences(): PreferenceEntity?

    @Query("UPDATE preferences SET currency = :currency, expenseFormat = :expenseFormat, decimalSeparator = :decimalSeparator, thousandSeparator = :thousandSeparator WHERE id = :id")
    suspend fun updatePreferences(
        id: Long,
        currency: String,
        expenseFormat: String,
        decimalSeparator: String,
        thousandSeparator: String
    )
}