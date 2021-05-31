package com.annchar.coinranking.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.IOException

@JsonClass(generateAdapter = true)
data class ApiExceptionBody(
    @Json(name = "error_code") val errorCode: Int,
    @Json(name = "error_message") val errorMessage: String
)

data class ApiException(
    val error: ApiExceptionBody?
) : IOException("${error?.errorCode} ${error?.errorMessage}")
