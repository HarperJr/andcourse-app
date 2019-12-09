package com.conceptic.andcourse.data.api.statistics

import com.conceptic.andcourse.data.api.ApiExecutor
import com.conceptic.andcourse.data.api.support.ConnectivityHandler

interface StatisticsApiExecutor

class StatisticsApiExecutorImpl(connectivityHandler: ConnectivityHandler, statisticsApi: StatisticsApi) :
    ApiExecutor<StatisticsApi>(connectivityHandler, statisticsApi), StatisticsApiExecutor
