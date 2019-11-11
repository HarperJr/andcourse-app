package com.conceptic.andcourse.usecase.questionnaire

import com.conceptic.andcourse.data.api.questionnaire.QuestionnaireApiExecutor
import com.conceptic.andcourse.data.model.Question
import com.conceptic.andcourse.data.repos.QuestionRepository
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class BeginQuestionnaireCase(
    private val questionnaireApiExecutor: QuestionnaireApiExecutor,
    private val questionRepository: QuestionRepository
) : UseCase<Unit, List<Question>> {
    override suspend fun execute(param: Unit): List<Question> = coroutineScope {
        val response = questionnaireApiExecutor
            .beginQuestionnaire()
        response.questions
            .map { Question(id = it.id, content = it.content) }
    }
}