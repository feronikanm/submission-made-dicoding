package com.fero.submission1made

import android.app.Application
import com.fero.submission1made.core.di.databaseModule
import com.fero.submission1made.core.di.networkModule
import com.fero.submission1made.core.di.repositoryModule
import com.fero.submission1made.di.useCaseModule
import com.fero.submission1made.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}