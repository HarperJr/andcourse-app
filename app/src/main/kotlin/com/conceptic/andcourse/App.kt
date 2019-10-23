package com.conceptic.andcourse

import android.app.Application
import com.conceptic.andcourse.di.AppModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    private val koinModules = listOf(AppModule.instance)

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            modules(koinModules)
        }
    }
}