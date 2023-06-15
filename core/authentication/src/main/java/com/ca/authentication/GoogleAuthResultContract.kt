package com.ca.authentication

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.ca.authentication.model.UserData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class GoogleAuthResultContract : ActivityResultContract<Unit, Result<UserData>>() {

    override fun createIntent(context: Context, input: Unit): Intent {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .requestId()
            .requestProfile()
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, googleSignInOptions).signInIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Result<UserData> {
        return GoogleSignIn
            .getSignedInAccountFromIntent(intent)
            .runCatching {
                getResult(ApiException::class.java).let { account ->
                    UserData(
                        id = account.id ?: throw IllegalStateException("Id not found, try later"),
                        idToken = account.idToken ?: throw IllegalStateException("IdToken not found, try later"),
                        email = account.email ?: throw IllegalStateException("Email not found, try later")
                    )
                }
            }
    }
}