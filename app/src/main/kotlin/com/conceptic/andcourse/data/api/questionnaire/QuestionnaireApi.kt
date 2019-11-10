package com.conceptic.andcourse.data.api.questionnaire

import com.conceptic.andcourse.data.api.questionnaire.model.QuestionNextResponse
import com.conceptic.andcourse.data.api.questionnaire.model.QuestionnaireBeginResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.POST

interface QuestionnaireApi {
    @POST("/questionnaire/begin")
    fun beginQuestionnaire(): Single<Response<QuestionnaireBeginResponse>>

    @POST("/questionnaire/next")
    fun nextQuestion(): Single<Response<QuestionNextResponse>>
}
