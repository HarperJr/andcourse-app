package com.conceptic.andcourse

import android.app.Application
import com.conceptic.andcourse.di.ApiModule
import com.conceptic.andcourse.di.AppModule
import com.conceptic.andcourse.di.UseCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {
    private val koinModules = listOf(AppModule(), UseCaseModule(), ApiModule())

    override fun onCreate() {
        super.onCreate()

        initTimber()
        initKoin()
    }

    private fun initTimber() = Timber.DebugTree()

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(koinModules)
        }
    }
}