package com.kpc.gallery

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.kpc.gallery.di.networkModule
import com.kpc.gallery.di.remoteDataSourceModule
import com.kpc.gallery.di.useCasesModule
import com.kpc.gallery.di.viewModelsModule

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                networkModule,
                remoteDataSourceModule,
                useCasesModule,
                viewModelsModule
            )
        }
    }
}