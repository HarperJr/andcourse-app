package com.conceptic.andcourse.usecase.statistics

import com.conceptic.andcourse.data.api.ApiExecutorFactory
import com.conceptic.andcourse.data.model.ChartViewType
import com.conceptic.andcourse.data.model.Statistics
import com.conceptic.andcourse.data.repos.StatisticsRepository
import com.conceptic.andcourse.usecase.UseCase

class StatisticsCase(
    apiExecutorFactory: ApiExecutorFactory,
    private val statisticsRepository: StatisticsRepository
) : UseCase<Unit, List<Statistics>> {
    private val statisticsApiExector = apiExecutorFactory.statisticsExecutor()

    override suspend fun execute(param: Unit): List<Statistics> {
        return listOf(Statistics(ChartViewType.CHART_BAR, "", Statistics.ChartData(emptyList(), emptyList())))
    }
}