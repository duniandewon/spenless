package com.ndewon.spendless

import android.app.Application
import com.ndewon.spendless.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApp)
            androidLogger(Level.DEBUG)
            modules(appModule)
        }
    }
}