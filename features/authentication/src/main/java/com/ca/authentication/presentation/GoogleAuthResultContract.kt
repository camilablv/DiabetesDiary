package com.ca.authentication.presentation

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.ca.authentication.BuildConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class GoogleAuthResultContract : ActivityResultContract<Unit, Result<String>>() {

    override fun createIntent(context: Context, input: Unit): Intent {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .requestId()
            .requestProfile()
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, googleSignInOptions).signInIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Result<String> {
        return GoogleSignIn
            .getSignedInAccountFromIntent(intent)
            .runCatching {
                getResult(ApiException::class.java).idToken
                    ?: throw IllegalStateException("Auth token not found, try later")
            }
    }
}