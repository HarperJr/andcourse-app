package com.conceptic.andcourse.data.api.auth.model

import java.io.Serializable

data class SignUpRequest(
    val name: String,
    val surname: String,
    val password: String,
    val dateBirth: String,
    val email: String,
    val gender: Int
) : Serializable
