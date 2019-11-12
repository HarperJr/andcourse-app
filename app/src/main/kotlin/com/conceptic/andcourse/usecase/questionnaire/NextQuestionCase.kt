package com.conceptic.andcourse.usecase.questionnaire

import com.conceptic.andcourse.data.api.questionnaire.QuestionnaireApiExecutor
import com.conceptic.andcourse.data.api.questionnaire.model.NextQuestionRequest
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class NextQuestionCase(
    private val questionnaireApiExecutor: QuestionnaireApiExecutor
) : UseCase<NextQuestionParams, String> {
    override suspend fun execute(param: NextQuestionParams): String = coroutineScope {
        questionnaireApiExecutor.nextQuestion(NextQuestionRequest(0, 0L))
        "Stub"
    }
}