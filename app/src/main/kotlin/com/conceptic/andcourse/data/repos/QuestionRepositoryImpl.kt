package com.conceptic.andcourse.data.repos

import com.conceptic.andcourse.data.database.dao.QuestionDao
import com.conceptic.andcourse.data.database.mapping.QuestionMapper
import com.conceptic.andcourse.data.model.Question
import kotlinx.coroutines.coroutineScope

interface QuestionRepository {
    suspend fun drop()
    suspend fun store(questions: List<Question>)
    suspend fun questions(): List<Question>
    suspend fun find(question: String): Question?
}

class QuestionRepositoryImpl(
    private val dao: QuestionDao
) : QuestionRepository {
    override suspend fun drop() = coroutineScope { dao.drop() }

    override suspend fun store(questions: List<Question>) = coroutineScope {
        dao.insert(questions.map { QuestionMapper.toEntity(it) })
    }

    override suspend fun questions(): List<Question> = coroutineScope {
        dao.all().map { QuestionMapper.toModel(it) }
    }

    override suspend fun find(question: String): Question? = coroutineScope {
        dao.find(question)?.let { QuestionMapper.toModel(it) }
    }
}