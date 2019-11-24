package com.conceptic.andcourse.data.database.mapping

import com.conceptic.andcourse.data.database.entity.QuestionEntity
import com.conceptic.andcourse.data.model.Question

object QuestionMapper :
    Mapper<QuestionEntity, Question> {
    override fun toModel(t: QuestionEntity): Question = Question(
        id = t.id,
        order = t.order,
        content = t.content
    )

    override fun toEntity(e: Question): QuestionEntity = QuestionEntity(
        id = e.id,
        order = e.order,
        content = e.content
    )
}