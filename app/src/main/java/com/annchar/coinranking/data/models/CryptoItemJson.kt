package com.annchar.coinranking.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptoItemJson(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "symbol") val symbol: String,
    @Json(name = "cmc_rank") val cmcRank: String,
    @Json(name = "quote") val quote: QuoteJson,
) {
    @JsonClass(generateAdapter = true)
    data class QuoteJson(
        @Json(name = "USD") val usd: UsdJson
    ) {
        @JsonClass(generateAdapter = true)
        data class UsdJson(
            @Json(name = "price") val price: Double,
            @Json(name = "volume_24h") val volume24h: Double,
            @Json(name = "percent_change_24h") val percentChange24h: Double,
            @Json(name = "market_cap") val marketCap: Double
        )
    }
}