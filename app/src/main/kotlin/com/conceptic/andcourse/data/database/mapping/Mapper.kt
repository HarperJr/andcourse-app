package com.conceptic.andcourse.data.database.mapping

interface Mapper<Entity, Model> {
    fun toModel(entity: Entity): Model

    fun toEntity(model: Model): Entity
}