package com.annchar.coinranking.base

import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException

const val MESSAGE_NETWORK_ERROR = "Network Error"
const val MESSAGE_SOMETHING_WENT_WRONG = "Something went wrong"
const val MESSAGE_UNKNOWN = "Unknown"

interface BaseRepository {
    suspend fun <T> request(
        call: suspend () -> T
    ): ApiResponse<T> {
        return try {
            ApiResponse.Success(call())
        } catch (exception: Exception) {
            when (exception) {
                is IOException -> {
                    ApiResponse.NetworkError(exception, ErrorResponse(message = MESSAGE_NETWORK_ERROR))
                }
                is HttpException -> {
                    val code = exception.code()
                    val errorResponse = convertErrorBody(exception)
                    if (errorResponse == null) {
                        ApiResponse.Error(code, exception, ErrorResponse(message = MESSAGE_SOMETHING_WENT_WRONG))
                    } else {
                        ApiResponse.Error(code, exception, errorResponse)
                    }
                }
                else -> {
                    ApiResponse.Error(exception = exception, error = ErrorResponse(message = MESSAGE_UNKNOWN))
                }
            }
        }
    }


    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }
}