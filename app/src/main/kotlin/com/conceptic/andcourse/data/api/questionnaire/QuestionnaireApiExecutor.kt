package com.conceptic.andcourse.data.api.questionnaire

import android.content.Context
import com.conceptic.andcourse.data.api.ApiExecutor
import com.conceptic.andcourse.data.api.questionnaire.model.*

/**
 * ApiExecutor's interface
 */
interface QuestionnaireApiExecutor {
    suspend fun beginQuestionnaire(): QuestionnaireBeginResponse

    suspend fun nextQuestion(request: NextQuestionRequest): QuestionNextResponse

    suspend fun completeQuestionnaire(): CompleteQuestionnaireResponse

    suspend fun features(): SummaryResponse
}

class QuestionnaireApiExecutorImpl(context: Context, questionnaireApi: QuestionnaireApi) :
    ApiExecutor<QuestionnaireApi>(context, questionnaireApi), QuestionnaireApiExecutor {
    override suspend fun beginQuestionnaire() = executeService { beginQuestionnaireAsync() }

    override suspend fun nextQuestion(request: NextQuestionRequest) = executeService { nextQuestionAsync(request) }

    override suspend fun completeQuestionnaire() = executeService { completeQuestionnaireAsync() }

    override suspend fun features(): SummaryResponse = executeService { featuresAsync() }
}