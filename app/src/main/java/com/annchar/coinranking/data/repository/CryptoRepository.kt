package com.annchar.coinranking.data.repository

import com.annchar.coinranking.data.api.APIService
import com.annchar.coinranking.data.models.mapper.CryptoMapper
import com.annchar.coinranking.models.CryptoItemResponse

interface CryptoRepository {
    suspend fun getCryptoList(start: Int, limit: Int): List<CryptoItemResponse>
}

class CryptoRepositoryImpl(
    private val service: APIService,
    private val mapper: CryptoMapper
) : CryptoRepository {
    override suspend fun getCryptoList(start: Int, limit: Int): List<CryptoItemResponse> {
        return mapper.toCryptoListResponse(service.getCryptoList(start, limit).data)
    }
}