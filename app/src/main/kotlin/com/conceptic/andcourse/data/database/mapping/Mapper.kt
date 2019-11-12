package com.conceptic.andcourse.data.database.mapping

interface Mapper<T, E> {
    fun toModel(t: T): E

    fun toEntity(e: E): T
}