package com.ca.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

enum class SignInMethod {
    ANONYMOUS,
    GOOGLE
}

open class AuthProvider {

    protected val auth: FirebaseAuth by lazy { Firebase.auth }

    val isUserSignedIn: Boolean
        get() = auth.currentUser != null

    fun signInMethod(): SignInMethod {
        return if(auth.currentUser?.isAnonymous == true) SignInMethod.ANONYMOUS else SignInMethod.GOOGLE
    }

    fun signOut() = auth.signOut()
}