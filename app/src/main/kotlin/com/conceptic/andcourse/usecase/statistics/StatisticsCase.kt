package com.conceptic.andcourse.usecase.statistics

import com.conceptic.andcourse.data.api.statistics.StatisticsApiExecutor
import com.conceptic.andcourse.data.model.Statistics
import com.conceptic.andcourse.usecase.UseCase

class StatisticsCase(
    private val statisticsApiExector: StatisticsApiExecutor
) : UseCase<StatisticsParam, Statistics> {
    override suspend fun execute(param: StatisticsParam): Statistics {
        //TODO implement this
        return Statistics()
    }
}
