package com.conceptic.andcourse.data.database.mapping

import com.conceptic.andcourse.data.database.entity.QuestionEntity
import com.conceptic.andcourse.data.model.Question

object QuestionMapper :
    Mapper<QuestionEntity, Question> {
    override fun toModel(t: QuestionEntity): Question = Question(
        id = t.id,
        content = t.content
    )

    override fun toEntity(e: Question): QuestionEntity = QuestionEntity(
        id = e.id,
        content = e.content
    )

    override fun toModel(tList: List<QuestionEntity>): List<Question> = tList.map { QuestionMapper.toModel(it) }

    override fun toEntity(eList: List<Question>): List<QuestionEntity> = eList.map { QuestionMapper.toEntity(it) }
}