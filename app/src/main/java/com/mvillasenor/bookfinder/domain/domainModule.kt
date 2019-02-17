package com.mvillasenor.bookfinder.domain

import com.mvillasenor.bookfinder.IO_DISPATCHER
import com.mvillasenor.bookfinder.domain.usecases.SearchBook
import kotlinx.coroutines.Deferred
import org.koin.dsl.module.module

val domainModule = module {
    single<(String, Int) -> Deferred<SearchResult>> { SearchBook(get(), get(name = IO_DISPATCHER)) }
}