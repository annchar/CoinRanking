package com.annchar.coinranking.data.api

import com.annchar.coinranking.data.models.CryptoListJson
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getCryptoList(
        @Query("start") start: Int,
        @Query("limit") limit: Int
    ): CryptoListJson
}