package com.ca.authentication

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class FirebaseAuthProvider(private val auth: FirebaseAuth) {

    val isUserSignedIn: Boolean
        get() = auth.currentUser != null

    fun signOut() = auth.signOut()

    private fun credential(idToken: String): AuthCredential {
        return GoogleAuthProvider.getCredential(idToken, null)
    }

    fun signInWithGoogle(token: String) {
        val credential = credential(token)
        auth.signInWithCredential(credential).addOnCompleteListener {

        }
    }
}