package com.conceptic.andcourse.data.database.mapping

import com.conceptic.andcourse.data.database.entity.FeatureEntity
import com.conceptic.andcourse.data.model.Feature
import com.conceptic.andcourse.data.model.FeatureType

object FeatureMapper : Mapper<FeatureEntity, Feature> {
    override fun toModel(entity: FeatureEntity): Feature = Feature(
        type = FeatureType.of(entity.type),
        featureDescription = entity.featureDescription,
        points = entity.points,
        maxPoints = entity.maxPoints
    )

    override fun toEntity(model: Feature): FeatureEntity = FeatureEntity(
        type = model.type.ordinal,
        featureDescription = model.featureDescription,
        points = model.points,
        maxPoints = model.maxPoints
    )
}