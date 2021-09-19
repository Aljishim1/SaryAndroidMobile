package com.sary.demo

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.sary.demo.di.viewModelModule
import com.sary.demo.di.repositoryModule
import com.sary.demo.di.serviceAPIModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(
                viewModelModule,
                repositoryModule,
                serviceAPIModule
            ))
        }

    }
}