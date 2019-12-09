package com.conceptic.andcourse.data.api.statistics

import com.conceptic.andcourse.data.api.ApiExecutor

interface StatisticsApiExecutor

class StatisticsApiExecutorImpl(statisticsApi: StatisticsApi) :
    ApiExecutor<StatisticsApi>(statisticsApi), StatisticsApiExecutor
