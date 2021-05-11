package com.annchar.coinranking.data.repository

import com.annchar.coinranking.base.ApiResponse
import com.annchar.coinranking.base.BaseRepository
import com.annchar.coinranking.data.api.APIService
import com.annchar.coinranking.data.models.CryptoListJson

interface CryptoRepository : BaseRepository {
    suspend fun getCryptoList(start: Int, limit: Int): ApiResponse<CryptoListJson>
}

class CryptoRepositoryImpl(private val service: APIService) : CryptoRepository {
    override suspend fun getCryptoList(start: Int, limit: Int): ApiResponse<CryptoListJson> {
        return request {
            service.getCryptoList(start, limit)
        }
    }
}