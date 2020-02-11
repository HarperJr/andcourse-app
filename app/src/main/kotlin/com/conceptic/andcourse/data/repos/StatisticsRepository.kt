package com.conceptic.andcourse.data.repos

import com.conceptic.andcourse.data.database.dao.StatisticsDao
import com.conceptic.andcourse.data.database.mapping.StatisticsMapper
import com.conceptic.andcourse.data.model.Statistics
import kotlinx.coroutines.coroutineScope

interface StatisticsRepository {
    suspend fun drop()
    suspend fun store(statistics: List<Statistics>)
    suspend fun statistics(): List<Statistics>
}

class StatisticsRepositoryImpl(
    private val dao: StatisticsDao
) : StatisticsRepository {
    override suspend fun drop() {
        dao.drop()
    }

    override suspend fun store(statistics: List<Statistics>) = coroutineScope {
        dao.insert(statistics.map { StatisticsMapper.toEntity(it) })
    }

    override suspend fun statistics(): List<Statistics> = coroutineScope {
        dao.statistics().map { StatisticsMapper.toModel(it) }
    }
}