package com.annchar.coinranking.ui.cryptolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.annchar.coinranking.data.pagingsources.CryptoListPagingSource
import com.annchar.coinranking.data.pagingsources.NETWORK_PAGE_SIZE
import com.annchar.coinranking.data.repository.CryptoListRepository
import com.annchar.coinranking.ui.base.BaseViewModel
import com.annchar.coinranking.ui.models.CryptoItemResponse

class CryptoListViewModel(private val repository: CryptoListRepository) : BaseViewModel() {

    private val _cryptoList: MutableLiveData<PagingData<CryptoItemResponse>> =
        Pager(PagingConfig(pageSize = NETWORK_PAGE_SIZE)) { CryptoListPagingSource(repository) }.liveData
            .cachedIn(viewModelScope)
            .let { it as MutableLiveData<PagingData<CryptoItemResponse>> }
    val cryptoList: LiveData<PagingData<CryptoItemResponse>> = _cryptoList
}