package com.conceptic.andcourse.data.api.auth.model

import java.io.Serializable

data class JwtTokenResponse(
    val jwt: String
) : Serializable
