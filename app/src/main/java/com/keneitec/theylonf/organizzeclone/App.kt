package com.keneitec.theylonf.organizzeclone

import android.app.Application
import com.keneitec.theylonf.organizzeclone.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                mainModule
            )
        }
    }
}
