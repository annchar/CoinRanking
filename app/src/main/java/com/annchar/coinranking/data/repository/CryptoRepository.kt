package com.annchar.coinranking.data.repository

import com.annchar.coinranking.base.ApiResponse
import com.annchar.coinranking.base.BaseRepository
import com.annchar.coinranking.data.api.APIService
import com.annchar.coinranking.data.models.mapper.CryptoMapper
import com.annchar.coinranking.models.CryptoItemResponse

interface CryptoRepository : BaseRepository {
    suspend fun getCryptoList(start: Int, limit: Int): ApiResponse<List<CryptoItemResponse>>
}

class CryptoRepositoryImpl(
    private val service: APIService,
    private val mapper: CryptoMapper
) : CryptoRepository {
    override suspend fun getCryptoList(start: Int, limit: Int): ApiResponse<List<CryptoItemResponse>> {
        return request {
            mapper.toCryptoListResponse(service.getCryptoList(start, limit).data)
        }
    }
}