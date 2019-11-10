package com.conceptic.andcourse.usecase.questionnaire

import com.conceptic.andcourse.data.api.questionnaire.QuestionnaireApiExecutor
import com.conceptic.andcourse.usecase.UseCase
import io.reactivex.Observable

class NextQuestionCase(
    private val questionnaireApiExecutor: QuestionnaireApiExecutor
) : UseCase<NextQuestionParams, String> {
    override fun execute(param: NextQuestionParams): Observable<String> {
        return questionnaireApiExecutor.nextQuestion()
            .map { "stub" }
    }
}