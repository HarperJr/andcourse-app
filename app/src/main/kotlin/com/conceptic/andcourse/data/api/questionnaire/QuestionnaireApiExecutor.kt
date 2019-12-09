package com.conceptic.andcourse.data.api.questionnaire

import com.conceptic.andcourse.data.api.ApiExecutor
import com.conceptic.andcourse.data.api.questionnaire.model.*
import com.conceptic.andcourse.data.api.support.ConnectivityHandler

/**
 * ApiExecutor's interface
 */
interface QuestionnaireApiExecutor {
    suspend fun beginQuestionnaire(): QuestionnaireBeginResponse

    suspend fun nextQuestion(request: NextQuestionRequest): QuestionNextResponse

    suspend fun completeQuestionnaire(): CompleteQuestionnaireResponse
}

class QuestionnaireApiExecutorImpl(connectivityHandler: ConnectivityHandler, questionnaireApi: QuestionnaireApi) :
    ApiExecutor<QuestionnaireApi>(connectivityHandler, questionnaireApi), QuestionnaireApiExecutor {
    override suspend fun beginQuestionnaire() = executeService { beginQuestionnaireAsync() }

    override suspend fun nextQuestion(request: NextQuestionRequest) = executeService { nextQuestionAsync(request) }

    override suspend fun completeQuestionnaire() = executeService { completeQuestionnaireAsync() }
}