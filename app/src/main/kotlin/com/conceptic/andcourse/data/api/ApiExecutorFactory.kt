package com.conceptic.andcourse.data.api

import android.content.Context
import com.conceptic.andcourse.data.api.auth.AuthApiExecutor
import com.conceptic.andcourse.data.api.auth.AuthApiExecutorImpl
import com.conceptic.andcourse.data.api.questionnaire.QuestionnaireApiExecutor
import com.conceptic.andcourse.data.api.questionnaire.QuestionnaireApiExecutorImpl
import com.conceptic.andcourse.data.api.statistics.StatisticsApiExecutor
import com.conceptic.andcourse.data.api.statistics.StatisticsApiExecutorImpl
import org.koin.core.KoinComponent
import org.koin.core.get

class ApiExecutorFactory(private val context: Context) : KoinComponent {
    fun authExecutor(): AuthApiExecutor = AuthApiExecutorImpl(context, get())

    fun questionnaireExecutor(): QuestionnaireApiExecutor = QuestionnaireApiExecutorImpl(context, get())

    fun statisticsExecutor(): StatisticsApiExecutor = StatisticsApiExecutorImpl(context, get())
}