package com.conceptic.andcourse.data.api.auth.model

data class CredentialsResponse(
    val id: String,
    val mail: String,
    val gender: Int,
    val role: Int,
    val dateBirth: Long,
    val dateRegistered: Long
)