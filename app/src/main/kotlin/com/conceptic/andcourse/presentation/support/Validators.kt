package com.conceptic.andcourse.presentation.support

/**
 * Validator interface for expression or values which are to be validated
 */
interface Validator {
    var onInvalid: (() -> Unit)?

    fun value(): String

    fun validate(): Boolean
}

interface ValidatorPredicate {
    fun validate(value: String): Boolean
}

infix fun Validator.orElse(onInvalid: () -> Unit) = this.apply {
    this.onInvalid = onInvalid
}

infix fun ValidatorPredicate.expression(expression: () -> String) = object : Validator {
    override var onInvalid: (() -> Unit)? = null

    override fun value(): String = expression.invoke()

    override fun validate(): Boolean = this@expression.validate(value())
}

infix fun ValidatorPredicate.value(value: String) = object : Validator {
    override var onInvalid: (() -> Unit)? = null

    override fun value(): String = value

    override fun validate(): Boolean = this@value.validate(value())
}

infix fun ValidatorPredicate.and(predicate: ValidatorPredicate) = object : ValidatorPredicate {
    override fun validate(value: String): Boolean = this@and.validate(value) && predicate.validate(value)

}

fun validateEmail() = object : ValidatorPredicate {
    override fun validate(value: String): Boolean = "[A-Za-z0-9_-]+@\\d+\\.(ru|com|org|net)".toRegex().matches(value)

}

fun validateLength(length: Int) = object : ValidatorPredicate {
    override fun validate(value: String): Boolean = value.length <= length

}

fun validateEquals(compareValue: String) = object : ValidatorPredicate {
    override fun validate(value: String): Boolean = value == compareValue

}

fun validateEquals(compareValue: Validator) = object : ValidatorPredicate {
    override fun validate(value: String): Boolean = value == compareValue.value()

}

fun validateRequired() = object : ValidatorPredicate {
    override fun validate(value: String): Boolean = value.isNotEmpty() && value.isNotBlank()

}

fun validateCustom(predicate: (String) -> Boolean) = object : ValidatorPredicate {
    override fun validate(value: String): Boolean = predicate.invoke(value)
}

fun validate(vararg validators: Validator, onValidated: () -> Unit) {
    var validated = true
    validators.forEach { validator ->
        if (!validator.validate()) {
            validator.onInvalid?.invoke()
            validated = false
        }
    }
    if (validated) onValidated.invoke()
}

