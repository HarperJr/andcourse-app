package com.conceptic.andcourse.usecase.questionnaire.next

import com.conceptic.andcourse.data.api.questionnaire.QuestionnaireApiExecutor
import com.conceptic.andcourse.data.api.questionnaire.model.NextQuestionRequest
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class NextQuestionCase(
    private val questionnaireApiExecutor: QuestionnaireApiExecutor
) : UseCase<NextQuestionParams, String?> {
    override suspend fun execute(param: NextQuestionParams): String? = coroutineScope {
        val response = questionnaireApiExecutor.nextQuestion(NextQuestionRequest(param.answer))
        response.nextQuestion
    }
}