package com.conceptic.andcourse.data.api.auth.model

import java.io.Serializable

data class SingUpRequest(
    val name: String,
    val surname: String,
    val password: String,
    val dateBirth: String,
    val email: String,
    val gender: Gender
) : Serializable

enum class Gender {
    MALE, FEMALE
}
