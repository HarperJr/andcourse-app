package com.conceptic.andcourse.usecase.statistics

import com.conceptic.andcourse.data.api.ApiExecutorFactory
import com.conceptic.andcourse.data.model.ChartValue
import com.conceptic.andcourse.data.model.ChartViewType
import com.conceptic.andcourse.data.model.Statistics
import com.conceptic.andcourse.data.repos.StatisticsRepository
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class StatisticsCase(
    apiExecutorFactory: ApiExecutorFactory,
    private val statisticsRepository: StatisticsRepository
) : UseCase<Unit, List<Statistics>> {
    private val statisticsApiExecutor = apiExecutorFactory.statisticsExecutor()

    override suspend fun execute(param: Unit): List<Statistics> = coroutineScope {
        val cachedStatistics = statisticsRepository.statistics()
        runCatching {
            val response = statisticsApiExecutor.statistics()
            response.let {
                it.statistics.map { statistic ->
                    val chartValues = statistic.data.map { value -> ChartValue(value.x, value.y, value.label) }
                    Statistics(
                        ChartViewType.of(statistic.chartViewType),
                        Statistics.ChartData(statistic.title, statistic.xAxisLabel, statistic.yAxisLabel, chartValues)
                    )
                }
            }
        }.onSuccess { statistics ->
            with(statisticsRepository) {
                drop()
                store(statistics)
            }
        }.getOrDefault(cachedStatistics)
    }
}