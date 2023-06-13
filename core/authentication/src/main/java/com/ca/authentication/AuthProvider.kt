package com.ca.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

open class AuthProvider {

    protected val auth: FirebaseAuth by lazy { Firebase.auth }

    val isUserSignedIn: Boolean
        get() = auth.currentUser != null

    fun signInMethod(): Boolean {
        return auth.currentUser?.isAnonymous == true
    }

    fun signOut() = auth.signOut()
}