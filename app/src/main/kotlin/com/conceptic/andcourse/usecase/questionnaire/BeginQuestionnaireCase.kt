package com.conceptic.andcourse.usecase.questionnaire

import com.conceptic.andcourse.data.api.questionnaire.QuestionnaireApiExecutor
import com.conceptic.andcourse.data.model.Question
import com.conceptic.andcourse.data.repos.QuestionRepository
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class BeginQuestionnaireCase(
    private val questionnaireApiExecutor: QuestionnaireApiExecutor,
    private val questionRepository: QuestionRepository
) : UseCase<Unit, Unit> {
    override suspend fun execute(param: Unit) = coroutineScope {
        val response = questionnaireApiExecutor
            .beginQuestionnaire()
        val questions = response.questions
            .map { Question(id = it.id, order = it.order, content = it.content) }
        questionRepository.storeQuestions(questions)
    }
}