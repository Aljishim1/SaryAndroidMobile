package com.sary.sary

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.sary.sary.di.categoriesViewModule
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
                categoriesViewModule
            ))
        }

    }
}