package com.mvillasenor.bookfinder.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mvillasenor.bookfinder.data.repositories.OpenLibraryRepository
import com.mvillasenor.bookfinder.data.repositories.RetrofitOpenLibraryRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        OkHttpClient.Builder().build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://openlibrary.org/")
            .build()
    }

    single<OpenLibraryRepository> { RetrofitOpenLibraryRepository(get()) }
}