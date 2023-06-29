package com.ca.authentication

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import com.google.firebase.auth.GoogleAuthProvider
import java.util.Calendar

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

    fun checkAndRefreshTokenIfNeeded() {
        if (isUserSignedIn) {
            auth.currentUser?.getIdToken(true)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val result: GetTokenResult = task.result
                        val expirationTimestamp: Long = result.expirationTimestamp
                        val currentTime = Calendar.getInstance().timeInMillis
                        val thirtyMinutesInMillis = 30 * 60 * 1000
                        if (expirationTimestamp - currentTime <= thirtyMinutesInMillis) {

                        }
                    }
                }
        }
    }
}