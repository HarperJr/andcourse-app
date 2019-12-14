package com.conceptic.andcourse.usecase.questionnaire.summary

import com.conceptic.andcourse.data.api.ApiExecutorFactory
import com.conceptic.andcourse.data.database.mapping.FeatureMapper
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
        val response = runCatching { questionnaireApiExecutor.summary() }.getOrNull()
        response?.let { summary ->
            summary.features.map { Feature(FeatureType.of(it.featureType), it.featureDescription, it.points) }
        } ?: summaryRepository.features()
    }
}