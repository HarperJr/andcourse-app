package com.conceptic.andcourse.data.api.questionnaire.model

data class QuestionnaireBeginResponse(
    val questions: List<Question>,
    val dateStarted: Long
) {
    data class Question(val id: String, val content: String)
}