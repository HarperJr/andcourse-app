package com.conceptic.andcourse.usecase.questionnaire.intro

import com.conceptic.andcourse.data.api.ApiExecutorFactory
import com.conceptic.andcourse.data.model.Question
import com.conceptic.andcourse.data.repos.QuestionRepository
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class BeginQuestionnaireCase(
    apiExecutorFactory: ApiExecutorFactory,
    private val questionRepository: QuestionRepository
) : UseCase<Unit, Unit> {
    private val questionnaireApiExecutor = apiExecutorFactory.questionnaireExecutor()

    override suspend fun execute(param: Unit) = coroutineScope {
        val response = questionnaireApiExecutor
            .beginQuestionnaire()
        val questions = response.questions
            .map { Question(id = it.id, order = it.order, content = it.content) }
        questionRepository.run {
            drop()
            store(questions)
        }
    }
}