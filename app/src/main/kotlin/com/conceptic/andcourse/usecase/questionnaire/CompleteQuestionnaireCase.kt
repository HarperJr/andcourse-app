package com.conceptic.andcourse.usecase.questionnaire

import com.conceptic.andcourse.data.api.questionnaire.QuestionnaireApiExecutor
import com.conceptic.andcourse.data.model.Feature
import com.conceptic.andcourse.data.model.FeatureType
import com.conceptic.andcourse.data.repos.FeatureRepository
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class CompleteQuestionnaireCase(
    private val featuresRepository: FeatureRepository,
    private val questionnaireApiExecutor: QuestionnaireApiExecutor
) : UseCase<Unit, Unit> {
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
