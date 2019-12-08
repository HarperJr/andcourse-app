package com.conceptic.andcourse.data.database.mapping

import com.conceptic.andcourse.data.database.entity.FeatureEntity
import com.conceptic.andcourse.data.model.Feature

object FeatureMapper : Mapper<FeatureEntity, Feature> {
    override fun toModel(entity: FeatureEntity): Feature = Feature(
        type = entity.type,
        description = entity.description,
        points = entity.points
    )

    override fun toEntity(model: Feature): FeatureEntity = FeatureEntity(
        type = model.type,
        description = model.description,
        points = model.points
    )
}