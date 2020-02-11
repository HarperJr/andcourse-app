package com.conceptic.andcourse.data.database.mapping

import com.conceptic.andcourse.data.database.entity.StatisticsEntity
import com.conceptic.andcourse.data.model.ChartViewType
import com.conceptic.andcourse.data.model.Statistics
import com.google.gson.Gson

object StatisticsMapper : Mapper<StatisticsEntity, Statistics> {
    private val gson = Gson()

    override fun toModel(entity: StatisticsEntity): Statistics = Statistics(
        chartViewType = ChartViewType.of(entity.chartViewType),
        data = gson.fromJson(entity.data, Statistics.ChartData::class.java)
    )

    override fun toEntity(model: Statistics): StatisticsEntity = StatisticsEntity(
        chartViewType = model.chartViewType.ordinal,
        data = gson.toJson(model.data)
    )
}