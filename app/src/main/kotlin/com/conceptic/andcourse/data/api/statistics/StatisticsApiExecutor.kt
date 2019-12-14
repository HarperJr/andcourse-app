package com.conceptic.andcourse.data.api.statistics

import android.content.Context
import com.conceptic.andcourse.data.api.ApiExecutor

interface StatisticsApiExecutor

class StatisticsApiExecutorImpl(context: Context, statisticsApi: StatisticsApi) :
    ApiExecutor<StatisticsApi>(context, statisticsApi), StatisticsApiExecutor
