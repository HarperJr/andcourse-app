package com.conceptic.andcourse.presentation.auth.signin

import android.os.Bundle
import android.view.View
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.createScope
import com.conceptic.andcourse.presentation.ext.validate
import kotlinx.android.synthetic.main.fragment_signin.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class SignInFragment : BaseFragment<SignInViewModel>(R.layout.fragment_signin) {
    override val scope: Scope = createScope(SIGNIN_SCOPE)
    override val viewModel: SignInViewModel by scope.viewModel(this)

    private val email
        get() = signin_input_email.validate {
            required(errorMessage = "Email is required")
            email(errorMessage = "Email doesn't match it's format")
        }
    private val password
        get() = signin_input_password.validate {
            required(errorMessage = "Password is required")
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signin_btn_accept.setOnClickListener {
            validate(email, password) {
                viewModel.onAcceptBtnClicked(email.value, password.value)
            }
        }
    }

    companion object {
        private const val SIGNIN_SCOPE = "signin_scope"
    }
}