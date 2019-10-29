package com.conceptic.andcourse.usecase.auth.signup

data class SignUpParams(
    val email: String,
    val password: String,
    val dateBirth: String,
    val gender: Int
)