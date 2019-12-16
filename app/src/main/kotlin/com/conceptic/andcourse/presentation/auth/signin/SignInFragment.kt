package com.conceptic.andcourse.presentation.auth.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.auth.signin.support.GoogleAuthHandler
import com.conceptic.andcourse.presentation.auth.signin.support.GoogleAuthResultCallback
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.validate
import com.conceptic.andcourse.presentation.view.LoadingProgressDialog
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.android.synthetic.main.fragment_signin.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : BaseFragment<SignInViewModel>(R.layout.fragment_signin) {
    override val viewModel: SignInViewModel by currentScope.viewModel(this)
    private val googleAuthHandler: GoogleAuthHandler by currentScope.inject()

    private val email
        get() = signin_input_email validate {
            required(errorMessage = "Email is required")
            email(errorMessage = "Email doesn't match it's format")
        }
    private val password
        get() = signin_input_password validate {
            required(errorMessage = "Password is required")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.signInSuccessLiveData.observe({ lifecycle }) {
            navController.popBackStack(
                R.id.summaryFragment,
                false
            )
        }
        viewModel.loadingProgressLiveData.observe({ lifecycle }) { loading -> setProgressVisible(loading) }

        googleAuthHandler.googleAuthResultCallback = googleAuthResultCallback
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        googleAuthHandler.onActivityResult(requestCode, requestCode, data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        signin_btn_signin.setOnClickListener {
            validate(email, password) {
                viewModel.onSignInBtnClicked(email.value, password.value)
            }
        }
        signin_btn_goto_signup.setOnClickListener { navController.navigate(R.id.action_signinFragment_to_signUpFragment) }
        signin_btn_skip.setOnClickListener { navController.popBackStack(R.id.summaryFragment, false) }
        signin_btn_with_google.setOnClickListener { googleAuthHandler.startSignInActivity(this) }
    }

    private fun setProgressVisible(visible: Boolean) = LoadingProgressDialog.setVisible(this, visible)

    private val googleAuthResultCallback = object : GoogleAuthResultCallback {
        override fun onSuccess(googleAccount: GoogleSignInAccount) {
            viewModel.onSignedInWithGoogle(googleAccount)
        }

        override fun onFailure(throwable: Throwable) {

        }
    }
}