package com.conceptic.andcourse.usecase.statistics

import com.conceptic.andcourse.data.api.ApiExecutorFactory
import com.conceptic.andcourse.data.model.Statistics
import com.conceptic.andcourse.data.model.StatisticsViewType
import com.conceptic.andcourse.usecase.UseCase

class StatisticsCase(
    apiExecutorFactory: ApiExecutorFactory
) : UseCase<StatisticsParam, Statistics> {
    private val statisticsApiExector = apiExecutorFactory.statisticsExecutor()

    override suspend fun execute(param: StatisticsParam): Statistics {
        //TODO implement this
        return Statistics(StatisticsViewType.CHART_COLUMNS, "", emptyList())
    }
}