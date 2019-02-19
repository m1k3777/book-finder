package com.mvillasenor.bookfinder

import android.app.Application
import android.os.StrictMode
import androidx.test.runner.AndroidJUnitRunner
import com.mvillasenor.bookfinder.koin.testDataModule
import org.koin.standalone.StandAloneContext.loadKoinModules


class BooksTestRunner : AndroidJUnitRunner() {

    override fun callApplicationOnCreate(app: Application?) {
        super.callApplicationOnCreate(app)
        val policy = StrictMode.ThreadPolicy.Builder().permitNetwork().build()
        StrictMode.setThreadPolicy(policy)
        loadKoinModules(testDataModule)
    }


}
