package com.conceptic.andcourse.data.model

enum class Gender {
    MALE, FEMALE;

    companion object {
        fun of(value: Int) = values().find { it.ordinal == value }
            ?: throw IllegalArgumentException("Unable to find gender by value=$value")
    }
}