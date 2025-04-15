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

fun provideDAO(database: AppDatabase) = database.userDao()

val appDataModule = module {
    single { provideDataBase(get()) }
    single { provideDAO(get()) }
}