package com.conceptic.andcourse.usecase

import io.reactivex.Observable

interface UseCase<T, E> {
    fun execute(param: T): Observable<E>
}