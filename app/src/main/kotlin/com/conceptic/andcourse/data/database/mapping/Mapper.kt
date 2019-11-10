package com.conceptic.andcourse.data.database.mapping

interface Mapper<T, E> {
    fun toModel(t: T): E

    fun toEntity(e: E): T

    fun toModel(tList: List<T>): List<E>

    fun toEntity(eList: List<E>): List<T>
}