package com.annchar.coinranking.data.repository

import com.annchar.coinranking.data.api.APIService
import com.annchar.coinranking.data.models.mapper.CryptoListMapper
import com.annchar.coinranking.ui.models.CryptoItemResponse

interface CryptoListRepository {
    suspend fun getCryptoList(start: Int, limit: Int): List<CryptoItemResponse>
}

class CryptoRepositoryImpl(
    private val service: APIService,
    private val mapper: CryptoListMapper
) : CryptoListRepository {
    override suspend fun getCryptoList(start: Int, limit: Int): List<CryptoItemResponse> {
        return mapper.toCryptoListResponse(service.getCryptoList(start, limit).data)
    }
}