package com.conceptic.andcourse.data.api.auth.model

data class SignInRequest(
    val email: String,
    val password: String
)