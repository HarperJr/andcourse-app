package com.conceptic.andcourse.data.database.mapping

import com.conceptic.andcourse.data.database.entity.QuestionEntity
import com.conceptic.andcourse.data.model.Question

object QuestionMapper :
    Mapper<QuestionEntity, Question> {
    override fun toModel(entity: QuestionEntity): Question = Question(
        id = entity.id,
        order = entity.order,
        content = entity.content
    )

    override fun toEntity(model: Question): QuestionEntity = QuestionEntity(
        id = model.id,
        order = model.order,
        content = model.content
    )
}