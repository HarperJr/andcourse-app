package com.conceptic.andcourse.data.model

enum class FeatureType {
    EXTRAVERSION_INTROVERSION,
    RIGIDITY_PLASTICITY,
    EXCITABILITY_BALANCE,
    REACTION_TEMPO,
    ACTIVITY_PASSIVITY,
    HONESTY_PRIVACY;

    companion object {
        fun of(value: Int) = values().find { it.ordinal == value }
            ?: throw IllegalArgumentException("Unable to find type by y = $value")
    }
}