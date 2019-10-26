package com.conceptic.andcourse.data.api.auth

import com.conceptic.andcourse.SharedPreferencesProvider

class JwtTokenProvider(private val sharedPreferencesProvider: SharedPreferencesProvider) {

    fun get(): String? = sharedPreferencesProvider.get(JWT_TOKEN)

    fun put(jwt: String) = sharedPreferencesProvider.put(JWT_TOKEN, jwt)

    companion object {
        private const val JWT_TOKEN = "jwt_token"

        fun bearerByJwt(jwt: String?) = "Bearer $jwt"
    }
}