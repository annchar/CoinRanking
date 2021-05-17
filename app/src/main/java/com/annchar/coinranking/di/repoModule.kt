package com.annchar.coinranking.di

import com.annchar.coinranking.data.repository.CryptoRepository
import com.annchar.coinranking.data.repository.CryptoRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single<CryptoRepository> { CryptoRepositoryImpl(get(), get()) }
}
