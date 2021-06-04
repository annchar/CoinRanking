package com.annchar.coinranking.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.annchar.coinranking.data.api.APIService
import com.annchar.coinranking.data.models.mapper.CryptoListMapper
import com.annchar.coinranking.data.pagingsources.CryptoListPagingSource
import com.annchar.coinranking.data.pagingsources.NETWORK_PAGE_SIZE
import com.annchar.coinranking.ui.models.CryptoItemResponse

interface CryptoListRepository {
    suspend fun getCryptoList(): LiveData<PagingData<CryptoItemResponse>>
}

class CryptoRepositoryImpl(
    private val service: APIService,
    private val mapper: CryptoListMapper
) : CryptoListRepository {
    override suspend fun getCryptoList(): LiveData<PagingData<CryptoItemResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CryptoListPagingSource(service, mapper)
            }
        ).liveData
    }
}