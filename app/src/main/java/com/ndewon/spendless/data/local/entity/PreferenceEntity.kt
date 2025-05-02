package com.ndewon.spendless.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "preferences")
data class PreferenceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val currency: String,
    val expenseFormat: String,
    val decimalSeparator: String,
    val thousandSeparator: String
)