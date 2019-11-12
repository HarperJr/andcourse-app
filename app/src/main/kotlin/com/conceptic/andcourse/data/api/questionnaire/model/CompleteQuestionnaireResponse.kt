package com.conceptic.andcourse.data.api.questionnaire.model

data class CompleteQuestionnaireResponse(val results: List<FeatureResult>) {
    data class FeatureResult(val featureType: Int, val featureDescription: String, val points: Int)
}