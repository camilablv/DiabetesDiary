package com.ca.authentication

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class GoogleAuthenticationProvider : AuthProvider() {

    fun getCredential(): AuthCredential {
        return GoogleAuthProvider.getCredential("", null)
    }

    fun signInWithGoogle() {

    }
}