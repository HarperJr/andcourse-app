package com.conceptic.andcourse.presentation.auth.signup

import android.os.Bundle
import android.view.View
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.createScope
import com.conceptic.andcourse.usecase.auth.signup.Gender
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class SignUpFragment : BaseFragment<SignUpViewModel>(R.layout.fragment_signup) {
    override val scope: Scope = createScope(SIGNUP_SCOPE)
    override val viewModel: SignUpViewModel by scope.viewModel(this)

    private val email
        get() = signup_input_email_value.text.toString()
    private val dateBirth
        get() = signup_input_date_birth_value.text.toString()
    private val password
        get() = signup_input_password_value.text.toString()
    private val repeatPassword
        get() = signup_input_rep_password_value.text.toString()
    private val gender
        get() = when (signup_radio_group_gender.checkedRadioButtonId) {
            signup_radio_male.id -> Gender.MALE
            signup_radio_female.id -> Gender.FEMALE
            else -> throw IllegalArgumentException("Unhandled id")
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signup_btn_accept.setOnClickListener {
            viewModel.onAcceptBtnClicked(email, dateBirth, password, repeatPassword, gender)
        }
    }

    companion object {
        private const val SIGNUP_SCOPE = "signup_scope"
    }
}