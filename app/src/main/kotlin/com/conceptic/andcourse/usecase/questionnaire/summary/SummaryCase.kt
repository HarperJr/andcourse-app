package com.conceptic.andcourse.usecase.questionnaire.summary

import com.conceptic.andcourse.data.model.Feature
import com.conceptic.andcourse.data.repos.FeatureRepository
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class SummaryCase(
    private val featureRepository: FeatureRepository
) : UseCase<Unit, List<Feature>> {
    override suspend fun execute(param: Unit): List<Feature> = coroutineScope {
        featureRepository.features()
    }
}