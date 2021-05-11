package com.annchar.coinranking.base

interface BaseRepository {
    suspend fun <T> request(
        call: suspend () -> T
    ): ApiResponse<T> {
        return try {
            ApiResponse.Success(call())
        } catch (exception: Exception) {
            ApiResponse.Error(exception)
        }
    }
}