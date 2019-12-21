package com.conceptic.andcourse.data.api.statistics

import android.content.Context
import com.conceptic.andcourse.data.api.ApiExecutor
import com.conceptic.andcourse.data.api.statistics.model.StatisticsResponse

interface StatisticsApiExecutor {
    suspend fun statistics(): StatisticsResponse
}

class StatisticsApiExecutorImpl(context: Context, statisticsApi: StatisticsApi) :
    ApiExecutor<StatisticsApi>(context, statisticsApi), StatisticsApiExecutor {
    override suspend fun statistics(): StatisticsResponse = executeService { statisticsAsync() }
}
