package com.conceptic.andcourse.usecase.auth.signup

data class SignUpParams(
    val name: String,
    val surname: String,
    val password: String,
    val dateBirth: String,
    val email: String,
    val gender: Gender
)

enum class Gender {
    MALE, FEMALE
}