package com.conceptic.andcourse.data.api.support

import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import com.conceptic.andcourse.data.api.auth.JwtTokenProvider

class JwtPayloadProvider(private val jwtTokenProvider: JwtTokenProvider) {
    val decodedJwt: DecodedJWT
        get() {
            val currentJwt = jwtTokenProvider.get()
            currentJwt?.let { jwt ->
                return if (prevDecodedJwt?.token == jwt) prevDecodedJwt!! else decodeJwt(jwt)
            } ?: throw IllegalStateException("Jwt token is undefined")
        }
    private var prevDecodedJwt: DecodedJWT? = null

    @Throws(IllegalStateException::class, IllegalArgumentException::class)
    inline fun <reified T> getClaim(claim: String): T? = decodedJwt.claims[claim]?.`as`(T::class.java)

    @Throws(IllegalArgumentException::class)
    private fun decodeJwt(jwt: String): DecodedJWT {
        return runCatching {
            JWT.decode(jwt)
        }.onSuccess { token ->
            if (token.issuer != ISSUER)
                throw IllegalArgumentException("Illegal jwt issuer")
        }.getOrThrow()
    }

    companion object {
        private const val ISSUER = "questionnaire-sample"
    }
}