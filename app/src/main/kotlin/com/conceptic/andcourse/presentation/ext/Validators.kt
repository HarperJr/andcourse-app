package com.conceptic.andcourse.presentation.ext

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

typealias ValidationPredicate = (value: String) -> Boolean

interface Validator {
    val value: String

    fun invoke(): Boolean
}

data class Validation(val errorMessage: String, val predicate: ValidationPredicate)

class ValidationScope(lazyValue: () -> String) {
    private val value by lazy(lazyValue)
    private val validations: MutableList<Validation> = mutableListOf()

    fun email(errorMessage: String) = this.also {
        validations.add(Validation(errorMessage) { value ->
            "[A-Za-z0-9_\\-]+@\\w+\\.(ru|com|org|net)".toRegex().matches(value)
        })
    }

    fun password(errorMessage: String) = this.also {
        validations.add(Validation(errorMessage) { value ->
            "[A-Za-z0-9]+".toRegex().matches(value)
        })
    }

    fun required(errorMessage: String) = this.also {
        validations.add(Validation(errorMessage) { value -> value.isNotEmpty() && value.isNotBlank() })
    }

    fun minLength(errorMessage: String, length: Int) = this.also {
        validations.add(Validation(errorMessage) { value -> value.length >= length })
    }

    fun maxLength(errorMessage: String, length: Int) = this.also {
        validations.add(Validation(errorMessage) { value -> value.length <= length })
    }

    fun matches(errorMessage: String, matchingValue: String) = this.also {
        validations.add(Validation(errorMessage) { value -> value == matchingValue })
    }

    fun custom(errorMessage: String, predicate: ValidationPredicate) = this.also {
        validations.add(Validation(errorMessage, predicate))
    }

    fun validate(onValid: () -> Unit, onInvalid: (String) -> Unit) {
        validations.forEach { validation ->
            if (!validation.predicate.invoke(value)) {
                onInvalid.invoke(validation.errorMessage)
                return
            }
            onValid.invoke()
        }
    }

}

@Throws(IllegalStateException::class)
fun TextInputLayout.validate(
    validationInvocations: ValidationScope.() -> ValidationScope
): Validator {
    val editTextView = this.editText as TextInputEditText?
        ?: throw IllegalStateException("Fatal error: input text layout doesn't contain input edit text view")
    val lazyValue = { editTextView.text.toString() }
    return object : Validator {
        override val value: String by lazy(lazyValue)

        override fun invoke(): Boolean {
            var validated = true
            validationInvocations.invoke(ValidationScope(lazyValue))
                .also { scope ->
                    scope.validate({ this@validate.error = null }, { errorMessage ->
                        this@validate.error = errorMessage
                        validated = false
                    })
                }
            return validated
        }
    }
}

fun validate(vararg validators: Validator, onInvalid: (() -> Unit)? = null, onValid: () -> Unit) {
    val validated = validators.reduceOther(true) { acc, validator -> acc!! && validator.invoke() } ?: true
    if (validated) {
        onValid.invoke()
    } else {
        onInvalid?.invoke()
    }
}