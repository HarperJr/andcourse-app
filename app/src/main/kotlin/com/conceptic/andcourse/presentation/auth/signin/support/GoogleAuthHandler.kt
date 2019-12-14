package com.conceptic.andcourse.presentation.auth.signin.support

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


class GoogleAuthHandler(context: Context) {
    var googleAuthResultCallback: GoogleAuthResultCallback? = null
    private val googleSignInClient = GoogleSignIn.getClient(
        context, GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
    )

    fun startSignInActivity(fragment: Fragment) =
        fragment.startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            runCatching { task.getResult(ApiException::class.java) }
                .onSuccess { googleAccount ->
                    googleAuthResultCallback?.onSuccess(googleAccount!!)
                }.onFailure { throwable -> googleAuthResultCallback?.onFailure(throwable) }
        }
    }

    companion object {
        private const val RC_SIGN_IN = 101
    }
}

interface GoogleAuthResultCallback {
    fun onSuccess(googleAccount: GoogleSignInAccount)

    fun onFailure(throwable: Throwable)
}