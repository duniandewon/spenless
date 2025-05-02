package com.ndewon.spendless.data.di

import android.app.Application
import androidx.room.Room
import com.ndewon.spendless.data.local.AppDatabase
import org.koin.dsl.module

fun provideDataBase(application: Application): AppDatabase = Room.databaseBuilder(
    application,
    AppDatabase::class.java,
    "spend_less_db"
).build()

fun provideUserDAO(database: AppDatabase) = database.userDao()
fun providePreferenceDAO(database: AppDatabase) = database.preferenceDao()

val appDataModule = module {
    single { provideDataBase(get()) }
    single { provideUserDAO(get()) }
    single { providePreferenceDAO(get()) }
}