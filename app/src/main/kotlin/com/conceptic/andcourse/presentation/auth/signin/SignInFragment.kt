package com.conceptic.andcourse.presentation.auth.signin

import android.os.Bundle
import android.view.View
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.createScope
import kotlinx.android.synthetic.main.fragment_signin.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class SignInFragment : BaseFragment<SignInViewModel>(R.layout.fragment_signin) {
    override val scope: Scope = createScope(SIGNIN_SCOPE)
    override val viewModel: SignInViewModel by scope.viewModel(this)

    private val email = { signin_input_email_value.text.toString() }
    private val pass = { signin_input_pass_value.text.toString() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signin_btn_accept.setOnClickListener {
            viewModel.onAcceptBtnClicked(email(), pass())
        }
    }

    companion object {
        private const val SIGNIN_SCOPE = "signin_scope"
    }
}