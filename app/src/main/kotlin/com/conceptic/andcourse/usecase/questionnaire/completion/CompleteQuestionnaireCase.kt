package com.conceptic.andcourse.usecase.questionnaire.completion

import com.conceptic.andcourse.data.api.ApiExecutorFactory
import com.conceptic.andcourse.data.model.Feature
import com.conceptic.andcourse.data.model.FeatureType
import com.conceptic.andcourse.data.repos.SummaryRepository
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class CompleteQuestionnaireCase(
    executorFactory: ApiExecutorFactory,
    private val featuresRepository: SummaryRepository
) : UseCase<Unit, Unit> {
    private val questionnaireApiExecutor = executorFactory.questionnaireExecutor()

    override suspend fun execute(param: Unit) = coroutineScope {
        val features = questionnaireApiExecutor.completeQuestionnaire().results.map { result ->
            Feature(FeatureType.of(result.featureType), result.featureDescription, result.points)
        }
        featuresRepository.run {
            drop()
            store(features)
        }
    }
}
