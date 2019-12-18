package com.conceptic.andcourse.usecase.auth.signin

import com.conceptic.andcourse.data.api.support.JwtTokenWrapper

object JwtTokenValidator {
    /**
     * There should be a lot of validation cases
     */
    fun valid(jwt: JwtTokenWrapper): Boolean {
        return jwt.issuer == ISSUER
    }

    private const val ISSUER = "questionnaire-sample"
}