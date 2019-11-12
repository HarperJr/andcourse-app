package com.conceptic.andcourse.usecase.auth.signup

import com.conceptic.andcourse.data.model.Gender
import java.util.*

data class SignUpParams(
    val email: String,
    val password: String,
    val dateBirth: Date,
    val gender: Gender
)