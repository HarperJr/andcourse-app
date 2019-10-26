package com.conceptic.andcourse.data.api.support

import com.conceptic.andcourse.data.api.auth.JwtTokenProvider
import okhttp3.Interceptor
import okhttp3.Response

object Interceptors {
    fun interceptJwtToken(chain: Interceptor.Chain, jwtTokenProvider: JwtTokenProvider): Response = with(chain) {
        val request = request().newBuilder()
            .addHeader(HEADER_AUTHENTICATE, JwtTokenProvider.bearerByJwt(jwtTokenProvider.get()))
            .build()
        proceed(request)
    }

    private const val HEADER_AUTHENTICATE = "Authenticate"
}