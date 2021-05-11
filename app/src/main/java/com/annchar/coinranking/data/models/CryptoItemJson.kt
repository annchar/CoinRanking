package com.annchar.coinranking.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptoItemJson(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "symbol") val symbol: String
)