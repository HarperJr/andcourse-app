package com.conceptic.andcourse.presentation.ext

inline fun <S, T> Array<out T>.reduceOther(defaultValue: S? = null, operation: (acc: S?, T) -> S): S? {
    if (isEmpty()) throw UnsupportedOperationException("Empty array can't be reduced.")
    var accumulator: S? = defaultValue
    for (i in 0 until this.size) {
        accumulator = operation.invoke(accumulator, this[i])
    }
    return accumulator
}