package com.conceptic.andcourse.data.api.statistics

import com.conceptic.andcourse.data.api.statistics.model.StatisticsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface StatisticsApi {
    @GET("/statistics/all")
    fun statisticsAsync(): Deferred<Response<StatisticsResponse>>
}