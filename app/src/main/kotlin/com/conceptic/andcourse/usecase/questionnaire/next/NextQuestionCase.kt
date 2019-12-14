package com.conceptic.andcourse.usecase.questionnaire.next

import com.conceptic.andcourse.data.api.ApiExecutorFactory
import com.conceptic.andcourse.data.api.questionnaire.model.NextQuestionRequest
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class NextQuestionCase(
    authApiExecutorFactory: ApiExecutorFactory
) : UseCase<NextQuestionParams, String?> {
    private val questionnaireApiExecutor = authApiExecutorFactory.questionnaireExecutor()

    override suspend fun execute(param: NextQuestionParams): String? = coroutineScope {
        val response = questionnaireApiExecutor.nextQuestion(NextQuestionRequest(param.answer))
        response.nextQuestion
    }
}