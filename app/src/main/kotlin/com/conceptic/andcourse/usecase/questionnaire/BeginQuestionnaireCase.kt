package com.conceptic.andcourse.usecase.questionnaire

import com.conceptic.andcourse.data.api.questionnaire.QuestionnaireApiExecutor
import com.conceptic.andcourse.data.model.Question
import com.conceptic.andcourse.usecase.UseCase
import io.reactivex.Observable

class BeginQuestionnaireCase(
    private val questionnaireApiExecutor: QuestionnaireApiExecutor
) : UseCase<Nothing, List<Question>> {
    override fun execute(param: Nothing): Observable<List<Question>> {
        return questionnaireApiExecutor
            .beginQuestionnaire()
            .map { result ->
                result.questions.map {
                    Question(it.id, it.content)
                }
            }
    }
}