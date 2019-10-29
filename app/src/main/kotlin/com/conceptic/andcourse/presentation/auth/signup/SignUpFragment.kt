package com.conceptic.andcourse.presentation.auth.signup

import android.os.Bundle
import android.view.View
import com.conceptic.andcourse.R
import com.conceptic.andcourse.data.model.Gender
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.createScope
import com.conceptic.andcourse.presentation.support.*
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class SignUpFragment : BaseFragment<SignUpViewModel>(R.layout.fragment_signup) {
    override val scope: Scope = createScope(SIGNUP_SCOPE)
    override val viewModel: SignUpViewModel by scope.viewModel(this)

    private val email
        get() = validateRequired() and validateEmail() expression { signup_input_email_value.text.toString() } orElse {

        }
    private val dateBirth
        get() = validateRequired() expression { signup_input_date_birth_value.text.toString() } orElse {

        }
    private val password
        get() = validateRequired() and validateLength(length = 8) expression { signup_input_password_value.text.toString() } orElse {

        }
    private val repeatPassword
        get() = validateRequired() and validateEquals(password) expression { signup_input_rep_password_value.text.toString() } orElse {

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
                    email.value(),
                    dateBirth.value(),
                    password.value(),
                    repeatPassword.value(),
                    gender
                )
            }
        }
    }

    companion object {
        private const val SIGNUP_SCOPE = "signup_scope"
    }
}
