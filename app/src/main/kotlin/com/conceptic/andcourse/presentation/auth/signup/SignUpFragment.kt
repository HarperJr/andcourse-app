package com.conceptic.andcourse.presentation.auth.signup

import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.createScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class SignUpFragment : BaseFragment<SignUpViewModel>(R.layout.fragment_signup) {
    override val scope: Scope = createScope(SIGNUP_SCOPE)
    override val viewModel: SignUpViewModel by scope.viewModel(this)

    companion object {
        private const val SIGNUP_SCOPE = "signup_scope"
    }
}