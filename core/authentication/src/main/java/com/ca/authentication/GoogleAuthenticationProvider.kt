package com.ca.authentication

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class GoogleAuthenticationProvider : AuthProvider() {

    private fun credential(idToken: String): AuthCredential {
        return GoogleAuthProvider.getCredential(idToken, null)
    }

    fun signInWithGoogle(token: String) {
        val credential = credential(token)
        auth.signInWithCredential(credential).addOnCompleteListener {

        }
    }

    fun linkWithGoogle(
        token: String,
        onSuccess: () -> Unit
    ) {
        val credential = credential(token)
        auth.currentUser!!.linkWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {

                }
            }
    }
}