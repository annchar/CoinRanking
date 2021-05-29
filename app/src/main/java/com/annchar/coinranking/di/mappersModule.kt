package com.annchar.coinranking.di

import com.annchar.coinranking.data.models.mapper.CryptoListMapper
import org.koin.dsl.module

val mappersModule = module {
    single { CryptoListMapper() }
}
