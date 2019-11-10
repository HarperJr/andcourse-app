package com.conceptic.andcourse.data.api.questionnaire

import com.conceptic.andcourse.data.api.ApiExecutor
import com.conceptic.andcourse.data.api.questionnaire.model.QuestionNextResponse
import com.conceptic.andcourse.data.api.questionnaire.model.QuestionnaireBeginResponse
import io.reactivex.Observable

/**
 * ApiExecutor's interface
 */
interface QuestionnaireApiExecutor {
    fun beginQuestionnaire(): Observable<QuestionnaireBeginResponse>

    fun nextQuestion(): Observable<QuestionNextResponse>
}

class QuestionnaireApiExecutorImpl(questionnaireApi: QuestionnaireApi) :
    ApiExecutor<QuestionnaireApi>(questionnaireApi), QuestionnaireApiExecutor {
    override fun beginQuestionnaire(): Observable<QuestionnaireBeginResponse> =
        executeService { beginQuestionnaire() }

    override fun nextQuestion(): Observable<QuestionNextResponse> =
        executeService { nextQuestion() }
}