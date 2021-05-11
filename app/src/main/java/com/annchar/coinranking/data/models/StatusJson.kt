package com.annchar.coinranking.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatusJson(
    @Json(name = "error_code") val errorCode: Int,
    @Json(name = "error_message") val errorMessage: String? = null
)