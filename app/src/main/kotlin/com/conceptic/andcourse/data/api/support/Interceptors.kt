package com.conceptic.andcourse.data.api.support

import com.conceptic.andcourse.data.api.auth.JwtTokenProvider
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

object Interceptors {
    fun jwtTokenInterceptor(jwtTokenProvider: JwtTokenProvider): Interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val request = request().newBuilder()
                .addHeader(HEADER_AUTHORIZATION, JwtTokenProvider.bearerByJwt(jwtTokenProvider.get()))
                .build()
            proceed(request)
        }
    }

    fun loggingInterceptor(loggingLevel: HttpLoggingInterceptor.Level) = HttpLoggingInterceptor()
        .apply { level = loggingLevel }

    private const val HEADER_AUTHORIZATION = "Authorization"
}