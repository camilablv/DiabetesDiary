package com.ca.authentication.presentation

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.ca.authentication.GoogleAuthenticationProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException

class GoogleAuthResultContract : ActivityResultContract<GoogleAuthenticationProvider, String>() {

    override fun createIntent(context: Context, input: GoogleAuthenticationProvider): Intent {
        return input.signInClient(context).signInIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String {
//        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
//        return task.result.idToken

        return try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
            val token = task.getResult(ApiException::class.java).idToken
                ?: throw IllegalStateException("Auth token not found, try later")
            token
        } catch (e: Exception) {
            val errorMsg = e.message ?: "Authentication failed"
            errorMsg
        }
    }
}