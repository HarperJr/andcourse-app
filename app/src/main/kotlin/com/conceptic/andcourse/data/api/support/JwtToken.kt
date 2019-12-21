package com.conceptic.andcourse.data.api.support

import com.auth0.android.jwt.Claim
import com.auth0.android.jwt.JWT
import java.util.*

class JwtToken(val rawJwt: String) {
    private val decodedJwt: JWT = JWT(rawJwt)
    val issuer: String
        get() = getClaim("iss")?.asString()!!
    val expiresAt: Date
        get() = getClaim("exp")?.asDate()!!

    @Throws(IllegalStateException::class, IllegalArgumentException::class)
    fun getClaim(claim: String): Claim? = decodedJwt.claims[claim]

    fun expired(): Boolean = expiresAt <= Date()
}