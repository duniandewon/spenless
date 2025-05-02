package com.ndewon.spendless.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ndewon.spendless.data.local.dao.PreferenceDao
import com.ndewon.spendless.data.local.dao.UserDao
import com.ndewon.spendless.data.local.entity.PreferenceEntity
import com.ndewon.spendless.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, PreferenceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun preferenceDao(): PreferenceDao
}