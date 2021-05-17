package com.annchar.coinranking.di

import com.annchar.coinranking.data.models.mapper.CryptoMapper
import org.koin.dsl.module

val mappersModule = module {
    single { CryptoMapper() }
}
