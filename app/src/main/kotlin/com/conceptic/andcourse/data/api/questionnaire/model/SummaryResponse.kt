package com.conceptic.andcourse.data.api.questionnaire.model

data class SummaryResponse(val dateStarted: Long, val datePassed: Long, val summaryRows: List<SummaryRow>)
