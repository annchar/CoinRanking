package com.annchar.coinranking.di

import com.annchar.coinranking.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://pro-api.coinmarketcap.com"

val networkModule = module {
    single<Interceptor> {
        HttpLoggingInterceptor()
            .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(get() as Interceptor)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val request = originalRequest.newBuilder()
                    .header("Accept", "application/json")
                    .addHeader("X-CMC_PRO_API_KEY", BuildConfig.API_KEY)
                    .method(originalRequest.method, originalRequest.body)
                chain.proceed(request.build())
            }
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get() as OkHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(get() as Moshi))
            .build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}