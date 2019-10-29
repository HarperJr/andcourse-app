package com.conceptic.andcourse.presentation.auth.signup

import android.os.Bundle
import android.view.View
import com.conceptic.andcourse.R
import com.conceptic.andcourse.data.model.Gender
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.createScope
import com.conceptic.andcourse.presentation.ext.validate
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class SignUpFragment : BaseFragment<SignUpViewModel>(R.layout.fragment_signup) {
    override val scope: Scope = createScope(SIGNUP_SCOPE)
    override val viewModel: SignUpViewModel by scope.viewModel(this)

    private val email
        get() = signup_input_email.validate {
            required(errorMessage = "Email is required")
            email(errorMessage = "Email doesn't match it's format")
        }
    private val dateBirth
        get() = signup_input_date_birth.validate {
            required(errorMessage = "Birth date is required")
        }
    private val password
        get() = signup_input_password.validate {
            required(errorMessage = "Password is required")
            password(errorMessage = "Password must contain at least one digit character")
            minLength(errorMessage = "Password must be at least 8 characters length", length = 8)
        }
    private val repeatPassword
        get() = signup_input_rep_password.validate {
            required(errorMessage = "Please repeat your password")
            matches(errorMessage = "Password doesn't match", matchingValue = password.value)
        }

    private val gender
        get() = when (signup_radio_group_gender.checkedRadioButtonId) {
            signup_radio_male.id -> Gender.MALE
            signup_radio_female.id -> Gender.FEMALE
            else -> throw IllegalArgumentException("Unhandled id")
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validate(email, password, repeatPassword) {
            signup_btn_accept.setOnClickListener {
                viewModel.onAcceptBtnClicked(
                    email.value,
                    dateBirth.value,
                    password.value,
                    repeatPassword.value,
                    gender
                )
            }
        }
    }

    companion object {
        private const val SIGNUP_SCOPE = "signup_scope"
    }
}
