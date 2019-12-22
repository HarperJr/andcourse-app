package com.conceptic.andcourse.usecase.questionnaire.summary

import com.conceptic.andcourse.data.api.ApiExecutorFactory
import com.conceptic.andcourse.data.api.auth.JwtTokenProvider
import com.conceptic.andcourse.data.model.Feature
import com.conceptic.andcourse.data.model.FeatureType
import com.conceptic.andcourse.data.repos.SummaryRepository
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class SummaryCase(
    apiExecutorFactory: ApiExecutorFactory,
    private val summaryRepository: SummaryRepository,
    private val tokenProvider: JwtTokenProvider
) : UseCase<Unit, List<Feature>> {
    private val questionnaireApiExecutor = apiExecutorFactory.questionnaireExecutor()

    override suspend fun execute(param: Unit): List<Feature> = coroutineScope {
        val token = tokenProvider.get()
        if (token == null || token.expired()) {
            summaryRepository.features()
        } else {
            val response = questionnaireApiExecutor.features()
            response.let {
                it.summaryRows.map { summaryRow ->
                    Feature(FeatureType.of(summaryRow.featureType), summaryRow.featureDescription, summaryRow.points)
                }
            }
        }
    }
}