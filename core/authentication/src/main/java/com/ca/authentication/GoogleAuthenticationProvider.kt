package com.ca.authentication

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class GoogleAuthenticationProvider : AuthProvider() {

    fun getCredential(): AuthCredential {
        return GoogleAuthProvider.getCredential("", null)
    }

    fun signInWithGoogle() {
        val signInOptions = BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
            .setSupported(true)
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .setFilterByAuthorizedAccounts(true)
            .build()

        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(signInOptions)
            .setAutoSelectEnabled(true)
            .build()
    }
}