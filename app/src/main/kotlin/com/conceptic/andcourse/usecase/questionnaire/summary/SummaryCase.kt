package com.conceptic.andcourse.usecase.questionnaire.summary

import com.conceptic.andcourse.data.api.ApiExecutorFactory
import com.conceptic.andcourse.data.model.Feature
import com.conceptic.andcourse.data.model.FeatureType
import com.conceptic.andcourse.data.repos.SummaryRepository
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class SummaryCase(
    apiExecutorFactory: ApiExecutorFactory,
    private val summaryRepository: SummaryRepository
) : UseCase<Unit, List<Feature>> {
    private val questionnaireApiExecutor = apiExecutorFactory.questionnaireExecutor()

    override suspend fun execute(param: Unit): List<Feature> = coroutineScope {
        val summary = summaryRepository.features()
        if (summary.isEmpty()) {
            val response = questionnaireApiExecutor.summary()
            response.let {
                it.features.map { feat ->
                    Feature(FeatureType.of(feat.featureType), feat.featureDescription, feat.points)
                }
            }
        } else emptyList()
    }
}