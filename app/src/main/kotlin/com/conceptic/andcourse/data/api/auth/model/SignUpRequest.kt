package com.conceptic.andcourse.data.api.auth.model

data class SignUpRequest(
    val email: String,
    val password: String,
    val dateBirth: String,
    val gender: Int
)
