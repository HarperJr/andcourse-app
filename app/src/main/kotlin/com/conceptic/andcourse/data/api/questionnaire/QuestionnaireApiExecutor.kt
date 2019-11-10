package com.conceptic.andcourse.data.api.questionnaire

import com.conceptic.andcourse.data.api.ApiExecutor
import com.conceptic.andcourse.data.api.questionnaire.model.*
import io.reactivex.Observable

/**
 * ApiExecutor's interface
 */
interface QuestionnaireApiExecutor {
    fun beginQuestionnaire(): Observable<QuestionnaireBeginResponse>

    fun nextQuestion(request: NextQuestionRequest): Observable<QuestionNextResponse>

    fun completeQuestionnaire(request: CompleteQuestionnaireRequest): Observable<CompleteQuestionnaireResponse>
}

class QuestionnaireApiExecutorImpl(questionnaireApi: QuestionnaireApi) :
    ApiExecutor<QuestionnaireApi>(questionnaireApi), QuestionnaireApiExecutor {
    override fun beginQuestionnaire(): Observable<QuestionnaireBeginResponse> =
        executeService { beginQuestionnaire() }

    override fun nextQuestion(request: NextQuestionRequest): Observable<QuestionNextResponse> =
        executeService { nextQuestion(request) }

    override fun completeQuestionnaire(request: CompleteQuestionnaireRequest): Observable<CompleteQuestionnaireResponse> =
        executeService { completeQuestionnaire(request) }
}