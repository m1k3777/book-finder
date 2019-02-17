package com.mvillasenor.bookfinder.ui.search

import com.mvillasenor.bookfinder.MAIN_DISPATCHER
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val searchModule = module {
    viewModel { SearchViewModel(get(MAIN_DISPATCHER), get()) }
}