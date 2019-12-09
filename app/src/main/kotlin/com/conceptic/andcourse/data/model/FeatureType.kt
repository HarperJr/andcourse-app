package com.conceptic.andcourse.data.model

enum class FeatureType(val positive: String, val negative: String) {
    EXTRAVERSION_INTROVERSION("Extraversion", "Introversion"),
    RIGIDITY_PLASTICITY("Rigidity", "Plasticity"),
    EXCITABILITY_BALANCE("Excitability", "Balance"),
    REACTION_TEMPO("Reaction", "Tempo"),
    ACTIVITY_PASSIVITY("Activity", "Passivity"),
    HONESTY_SECRECY("Honesty", "Secrecy");

    companion object {
        fun of(value: Int) = values().find { it.ordinal == value }
            ?: throw IllegalArgumentException("Unable to find type by value = $value")
    }
}