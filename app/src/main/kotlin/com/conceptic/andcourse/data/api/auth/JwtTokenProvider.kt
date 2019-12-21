package com.conceptic.andcourse.data.api.auth

import com.conceptic.andcourse.SharedPreferencesProvider
import com.conceptic.andcourse.data.api.support.JwtToken
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

class JwtTokenProvider(private val sharedPreferencesProvider: SharedPreferencesProvider) {
    private val jwtTokenChannel = Channel<JwtToken?>()

    fun get(): JwtToken? = sharedPreferencesProvider
        .get<String>(JWT_TOKEN)?.let { JwtToken(it) }

    fun put(jwt: JwtToken?) {
        jwt?.let {
            sharedPreferencesProvider.put(JWT_TOKEN, it.rawJwt)
        } ?: sharedPreferencesProvider.remove(JWT_TOKEN)
        runBlocking { jwtTokenChannel.send(jwt) }
    }

    @ExperimentalCoroutinesApi
    suspend fun observe(consumer: suspend (JwtToken?) -> Unit) {
        if (jwtTokenChannel.isEmpty)
            consumer.invoke(get())
        for (jwtToken in jwtTokenChannel)
            consumer.invoke(jwtToken)
    }

    companion object {
        private const val JWT_TOKEN = "jwt_token"

        fun bearerByJwt(jwt: String?) = "Bearer $jwt"
    }
}