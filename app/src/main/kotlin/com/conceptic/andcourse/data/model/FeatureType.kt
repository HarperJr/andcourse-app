package com.conceptic.andcourse.data.model

import androidx.annotation.StringRes
import com.conceptic.andcourse.R

enum class FeatureType(@StringRes val description: Int) {
    EXTRAVERSION_INTROVERSION(R.string.extraversion_introversion),
    RIGIDITY_PLASTICITY(R.string.rigidity_plasticity),
    EXCITABILITY_BALANCE(R.string.excitability_balance),
    REACTION_TEMPO(R.string.reaction_tempo),
    ACTIVITY_PASSIVITY(R.string.activity_passivity),
    HONESTY_PRIVACY(R.string.honesty_privacy);

    companion object {
        fun of(value: Int) = values().find { it.ordinal == value }
            ?: throw IllegalArgumentException("Unable to find type by y = $value")
    }
}