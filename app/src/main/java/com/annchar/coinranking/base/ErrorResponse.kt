package com.annchar.coinranking.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "error_code") val code: Int? = null,
    @Json(name = "error_message") val message: String? = null
)