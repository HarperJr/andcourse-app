package com.conceptic.andcourse.data.model

data class Feature(
    val type: FeatureType,
    val featureDescription: String,
    val points: Int,
    val maxPoints: Int
)