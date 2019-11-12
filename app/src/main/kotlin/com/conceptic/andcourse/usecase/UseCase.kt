package com.conceptic.andcourse.usecase

interface UseCase<T, E> {
    suspend fun execute(param: T): E
}