package com.conceptic.andcourse.usecase.auth.signin

import com.conceptic.andcourse.data.api.support.JwtToken

object JwtTokenValidator {
    /**
     * There should be a lot of validation cases
     */
    fun valid(jwt: JwtToken): Boolean {
        return jwt.issuer == ISSUER
    }

    private const val ISSUER = "questionnaire-sample"
}