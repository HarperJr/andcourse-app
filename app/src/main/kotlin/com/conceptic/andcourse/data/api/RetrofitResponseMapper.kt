package com.conceptic.andcourse.data.api

import retrofit2.Response

object RetrofitResponseMapper {
    @Throws(ApiException::class, Exception::class)
    fun <T> map(response: Response<T>): T {
        return if (response.isSuccessful) {
            response.body() ?: throw Exception("Response is null")
        } else {
            response.errorBody()?.let {
                throw ApiException(response.code(), it.string())
            } ?: throw ApiException(response.code(), response.message())
        }
    }
}