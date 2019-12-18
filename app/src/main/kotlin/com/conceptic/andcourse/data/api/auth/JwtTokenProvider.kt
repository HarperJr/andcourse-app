package com.conceptic.andcourse.data.api.auth

import com.conceptic.andcourse.SharedPreferencesProvider
import com.conceptic.andcourse.data.api.support.JwtTokenWrapper

class JwtTokenProvider(private val sharedPreferencesProvider: SharedPreferencesProvider) {
    fun get(): JwtTokenWrapper? = sharedPreferencesProvider.get<String>(JWT_TOKEN)
        ?.let { JwtTokenWrapper(it) }

    fun put(jwt: JwtTokenWrapper) = sharedPreferencesProvider.put(JWT_TOKEN, jwt.token)

    companion object {
        private const val JWT_TOKEN = "jwt_token"

        fun bearerByJwt(jwt: String?) = "Bearer $jwt"
    }
}