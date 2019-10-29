package com.conceptic.andcourse.usecase.auth.signup

import com.conceptic.andcourse.data.model.Gender

data class SignUpParams(
    val email: String,
    val password: String,
    val dateBirth: String,
    val gender: Gender
)