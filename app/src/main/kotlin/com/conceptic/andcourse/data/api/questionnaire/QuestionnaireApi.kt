package com.conceptic.andcourse.data.api.questionnaire

import com.conceptic.andcourse.data.api.questionnaire.model.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuestionnaireApi {
    @POST("/questionnaire/begin")
    fun beginQuestionnaireAsync(): Deferred<Response<QuestionnaireBeginResponse>>

    @POST("/questionnaire/next")
    fun nextQuestionAsync(@Body request: NextQuestionRequest): Deferred<Response<QuestionNextResponse>>

    @POST("questionnaire/complete")
    fun completeQuestionnaireAsync(): Deferred<Response<CompleteQuestionnaireResponse>>

    @GET("questionnaire/summary")
    fun summaryAsync(): Deferred<Response<SummaryResponse>>
}
