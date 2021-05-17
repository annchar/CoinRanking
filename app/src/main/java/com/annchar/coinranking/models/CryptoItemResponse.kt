package com.annchar.coinranking.models

import com.annchar.coinranking.extentions.toDecimals
import com.annchar.coinranking.extentions.toDecimalsNoComma
import com.annchar.coinranking.extentions.toDecimalsUnits
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptoItemResponse(
    val id: Int,
    val name: String,
    val symbol: String,
    val cmcRank: String,
    val price: Double,
    val volume24h: Double,
    val percentChange24h: Double,
    val marketCap: Double
) {
    val priceDisplay: String
        get() = price.toDecimals()

    val priceDisplayNoComma: String
        get() = price.toDecimalsNoComma()

    val percentChangeDisplay: String
        get() = percentChange24h.toDecimals()

    val volumeDisplay: String
        get() = volume24h.toDecimalsUnits()
}