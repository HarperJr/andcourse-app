package com.conceptic.andcourse.presentation.ext

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

typealias ValidationPredicate = (value: String) -> Boolean

interface Validator {
    val value: String

    operator fun invoke(): Boolean
}

data class Validation(val errorMessage: String, val predicate: ValidationPredicate)

class ValidationScope(private val value: () -> String) {
    private val validations: MutableList<Validation> = mutableListOf()
    var key = 0
        private set

    fun email(errorMessage: String) = regex(errorMessage, "[A-Za-z0-9_\\-]+@\\w+\\.\\w")

    fun numeric(errorMessage: String) = regex(errorMessage, "\\d+")

    fun symbolic(errorMessage: String) = regex(errorMessage, "\\w+")

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

    fun regex(errorMessage: String, regex: String) = this.also {
        validations.add(Validation(errorMessage) { value ->
            regex.toRegex().matches(value)
        })
    }

    fun custom(errorMessage: String, predicate: ValidationPredicate) = this.also {
        validations.add(Validation(errorMessage, predicate))
    }

    fun key(key: Int) = this.also {
        it.key = key
    }

    fun validate(onValid: () -> Unit, onInvalid: (String) -> Unit) {
        val valueDefinition = value.invoke()
        validations.forEach { validation ->
            if (!validation.predicate.invoke(valueDefinition)) {
                onInvalid.invoke(validation.errorMessage)
                return
            }
            onValid.invoke()
        }
    }
}

@Throws(IllegalStateException::class)
infix fun TextInputLayout.validate(
    validationInvocations: ValidationScope.() -> ValidationScope
): Validator {
    val valueDelegate = {
        val editTextView = this@validate.editText as TextInputEditText?
            ?: throw IllegalStateException("Fatal error: input text layout doesn't contain input edit text view")
        editTextView.text.toString()
    }
    return object : Validator {
        override val value: String = valueDelegate.invoke()

        override operator fun invoke(): Boolean {
            var validated = true
            validationInvocations.invoke(ValidationScope(valueDelegate))
                .also { scope ->
                    scope.validate({
                        this@validate.error = null
                    }, { errorMessage ->
                        this@validate.error = errorMessage
                        validated = false
                    })
                }
            return validated
        }
    }
}

fun validate(
    vararg validators: Validator,
    onInvalid: ((List<Validator>) -> Unit)? = null,
    onValid: () -> Unit
) {
    val invalidFields = validators.filter { !it.invoke() }
    if (invalidFields.isEmpty()) {
        onValid.invoke()
    } else {
        onInvalid?.invoke(invalidFields)
    }
}