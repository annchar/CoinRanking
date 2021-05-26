package com.annchar.coinranking.data.models

import com.annchar.coinranking.data.ApiExceptionBody
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorJson(
    val status: ApiExceptionBody
)