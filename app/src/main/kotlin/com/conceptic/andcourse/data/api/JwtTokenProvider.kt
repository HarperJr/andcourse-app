package com.conceptic.andcourse.data.api

import com.conceptic.andcourse.SharedPreferencesProvider

class JwtTokenProvider(private val sharedPreferencesProvider: SharedPreferencesProvider) {

    fun get(): String? = sharedPreferencesProvider.get(JWT_TOKEN)

    companion object {
        private const val JWT_TOKEN = "jwt_token"

        fun bearerByJwt(jwt: String?) = "Bearer $jwt"
    }
}