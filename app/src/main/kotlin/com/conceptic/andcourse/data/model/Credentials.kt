package com.conceptic.andcourse.data.model

import java.util.*

data class Credentials(
    val id: String,
    val mail: String,
    val gender: Gender,
    val role: Role,
    val dateRegistered: Date,
    val dateBirth: Date
)