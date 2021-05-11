package com.annchar.coinranking.di

import com.annchar.coinranking.ui.cryptolist.CryptoListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { CryptoListViewModel(get()) }
}
