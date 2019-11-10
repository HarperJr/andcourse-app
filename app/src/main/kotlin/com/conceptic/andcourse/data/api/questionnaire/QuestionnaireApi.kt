package com.conceptic.andcourse.data.api.questionnaire

import com.conceptic.andcourse.data.api.questionnaire.model.*
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface QuestionnaireApi {
    @POST("/questionnaire/begin")
    fun beginQuestionnaire(): Single<Response<QuestionnaireBeginResponse>>

    @POST("/questionnaire/next")
    fun nextQuestion(@Body request: NextQuestionRequest): Single<Response<QuestionNextResponse>>

    @POST("questionnaire/complete")
    fun completeQuestionnaire(@Body request: CompleteQuestionnaireRequest): Single<Response<CompleteQuestionnaireResponse>>
}
