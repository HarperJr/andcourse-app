package com.conceptic.andcourse.data.api.support

import com.conceptic.andcourse.data.api.auth.JwtTokenProvider
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

object Interceptors {
    fun jwtTokenInterceptor(jwtTokenProvider: JwtTokenProvider): Interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return with(chain) {
                val request = request().newBuilder()
                    .addHeader(HEADER_AUTHORIZATION, JwtTokenProvider.bearerByJwt(jwtTokenProvider.get()))
                    .build()
                proceed(request)
            }
        }
    }

    fun loggingInterceptor(): Interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Timber.tag(OK_HTTP_CLIENT_TAG).d(message)
        }
    })

    private const val OK_HTTP_CLIENT_TAG = "OkHttp"
    private const val HEADER_AUTHORIZATION = "Authorization"
}