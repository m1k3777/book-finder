package com.mvillasenor.bookfinder

import android.app.Application
import com.mvillasenor.bookfinder.data.dataModule
import com.mvillasenor.bookfinder.domain.domainModule
import com.mvillasenor.bookfinder.ui.search.searchModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class BooksApplication : Application() {

    private val koinModules = listOf(applicationModule, dataModule, domainModule, searchModule)

    override fun onCreate() {
        super.onCreate()
        startKoin(this, koinModules)

        Timber.plant(Timber.DebugTree())
    }
}