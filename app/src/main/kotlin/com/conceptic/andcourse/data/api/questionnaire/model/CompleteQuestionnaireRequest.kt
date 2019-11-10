package com.conceptic.andcourse.data.api.questionnaire.model

data class CompleteQuestionnaireRequest(val answers: List<Answer>) {
    data class Answer(val question: String, val answer: Answer, val answeringDuration: Long)
}