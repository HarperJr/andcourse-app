package com.conceptic.andcourse.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.createScope
import kotlinx.android.synthetic.main.fragment_auth.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class AuthFragment : BaseFragment<AuthViewModel>() {
    override val scope: Scope = createScope(AUTH_SCOPE)
    override val viewModel: AuthViewModel by scope.viewModel(this)

    private val email = { auth_input_email_value.text.toString() }
    private val pass = { auth_input_pass_value.text.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth_btn_accept.setOnClickListener {
            viewModel.onAcceptBtnClicked(email(), pass())
        }
    }

    companion object {
        private val AUTH_SCOPE = "AUTH_SCOPE"
    }
}