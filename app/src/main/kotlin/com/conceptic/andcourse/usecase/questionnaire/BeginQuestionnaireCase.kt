package com.conceptic.andcourse.usecase.questionnaire

import com.conceptic.andcourse.data.api.questionnaire.QuestionnaireApiExecutor
import com.conceptic.andcourse.data.model.Question
import com.conceptic.andcourse.data.repos.QuestionRepository
import com.conceptic.andcourse.usecase.UseCase
import io.reactivex.Observable

class BeginQuestionnaireCase(
    private val questionnaireApiExecutor: QuestionnaireApiExecutor,
    private val questionRepository: QuestionRepository
) : UseCase<Unit, List<Question>> {
    override fun execute(param: Unit): Observable<List<Question>> {
        return questionnaireApiExecutor
            .beginQuestionnaire()
            .map { result ->
                val questions = result.questions.map {
                    Question(it.id, it.content)
                }
                questionRepository.insert(questions)
                questions
            }
    }
}