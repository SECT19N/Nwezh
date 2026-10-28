package com.section.nwezh

import android.app.Application
import com.section.nwezh.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class NwezhApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@NwezhApplication)
        }
    }
}
