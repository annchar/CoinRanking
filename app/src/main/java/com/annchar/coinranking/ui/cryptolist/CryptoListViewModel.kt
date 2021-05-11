package com.annchar.coinranking.ui.cryptolist

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.annchar.coinranking.base.ApiResponse
import com.annchar.coinranking.base.BaseViewModel
import com.annchar.coinranking.data.repository.CryptoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CryptoListViewModel(private val repository: CryptoRepository) : BaseViewModel() {

    fun loadCryptoList() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository.getCryptoList(1, 100)
            }
            when (response) {
                is ApiResponse.Success -> {
                    Log.e("ddd", "data: ${response.data}")
                }
                is ApiResponse.Error -> {
                    Log.e("ddd", "exception: ${response.exception}")
                }
            }
        }
    }
}