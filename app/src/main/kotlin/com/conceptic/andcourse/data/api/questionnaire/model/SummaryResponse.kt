package com.conceptic.andcourse.data.api.questionnaire.model

data class SummaryResponse(val dateStarted: Long, val datePassed: Long, val features: List<FeatureResult>)
