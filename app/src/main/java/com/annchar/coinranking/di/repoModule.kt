package com.annchar.coinranking.di

import com.annchar.coinranking.data.repository.CryptoListRepository
import com.annchar.coinranking.data.repository.CryptoRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single<CryptoListRepository> { CryptoRepositoryImpl(get(), get()) }
}
