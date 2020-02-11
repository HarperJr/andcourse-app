package com.conceptic.andcourse.presentation.questionnaire.summary.adapter

import android.content.Context
import com.conceptic.andcourse.R
import com.conceptic.andcourse.data.model.Feature
import com.conceptic.andcourse.data.model.FeatureType

class SummaryFeatureDescriptionResolver(private val context: Context) {
    fun resolveAnnotation(feature: Feature): String = when (feature.type) {
        FeatureType.EXTRAVERSION_INTROVERSION -> resolveExtraversionIntroversion(feature.points)
        FeatureType.RIGIDITY_PLASTICITY -> resolveRigidityPlasticity(feature.points)
        FeatureType.EXCITABILITY_BALANCE -> resolveExcitabiltyBalance(feature.points)
        FeatureType.REACTION_TEMPO -> resolveReactionTempo(feature.points)
        FeatureType.ACTIVITY_PASSIVITY -> resolveActivityPassivity(feature.points)
        FeatureType.HONESTY_PRIVACY -> resolveHonestyPrivacy(feature.points)
    }

    private fun resolveHonestyPrivacy(points: Int): String = context.getString(
        when (points) {
            in 0..10 -> R.string.privacy
            in 11..14 -> R.string.honesty_privacy_middle
            in 15..20 -> R.string.honesty
            else -> -1
        }
    )

    private fun resolveExtraversionIntroversion(points: Int): String = context.getString(
        when (points) {
            in 0..10 -> R.string.introversion
            in 11..14 -> R.string.introversion_introversion_middle
            in 15..20 -> R.string.extraversion
            else -> -1
        }
    )

    private fun resolveRigidityPlasticity(points: Int): String = context.getString(
        when (points) {
            in 0..10 -> R.string.rigidity
            in 11..14 -> R.string.rigidity_plasticity_middle
            in 15..20 -> R.string.plasticity
            else -> -1
        }
    )

    private fun resolveActivityPassivity(points: Int): String = context.getString(
        when (points) {
            in 0..10 -> R.string.activity
            in 11..14 -> R.string.activity_passivity_middle
            in 15..20 -> R.string.passivity
            else -> -1
        }
    )

    private fun resolveExcitabiltyBalance(points: Int): String = context.getString(
        when (points) {
            in 0..10 -> R.string.excitability
            in 11..14 -> R.string.excitability_balance_middle
            in 15..20 -> R.string.balance
            else -> -1
        }
    )

    private fun resolveReactionTempo(points: Int): String = context.getString(
        when (points) {
            in 0..10 -> R.string.reaction
            in 11..14 -> R.string.reaction_tempo_middle
            in 15..20 -> R.string.tempo
            else -> -1
        }
    )
}
