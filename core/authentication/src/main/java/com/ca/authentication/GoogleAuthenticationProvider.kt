package com.ca.authentication

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class GoogleAuthenticationProvider : AuthProvider() {

    fun getCredential(): AuthCredential {
        return GoogleAuthProvider.getCredential("", null)
    }

    fun signInClient(context: Context): GoogleSignInClient {
        val googleSignInOptions = signInOptions()
        return GoogleSignIn.getClient(context, googleSignInOptions)
    }

    private fun signInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .requestId()
            .requestProfile()
            .requestEmail()
            .build()
    }
}