package com.conceptic.andcourse.data.model

enum class Role {
    DEFAULT, OBSERVER;

    companion object {
        fun of(value: Int) = values().find { it.ordinal == value }
            ?: throw IllegalArgumentException("Unable to find role by value=$value")
    }
}