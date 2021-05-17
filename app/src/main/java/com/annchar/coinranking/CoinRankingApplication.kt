package com.annchar.coinranking

import android.app.Application
import com.annchar.coinranking.di.apiServicesModule
import com.annchar.coinranking.di.mappersModule
import com.annchar.coinranking.di.networkModule
import com.annchar.coinranking.di.repoModule
import com.annchar.coinranking.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CoinRankingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@CoinRankingApplication)
            modules(networkModule, repoModule, apiServicesModule, viewModelsModule, mappersModule)
        }
    }
}