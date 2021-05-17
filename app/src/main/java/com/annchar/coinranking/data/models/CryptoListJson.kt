package com.annchar.coinranking.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptoListJson(
    @Json(name = "data") val data: List<CryptoItemJson>? = emptyList()
)