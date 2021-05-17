package com.annchar.coinranking.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptoListResponse(
    val items: List<CryptoItemResponse>
)