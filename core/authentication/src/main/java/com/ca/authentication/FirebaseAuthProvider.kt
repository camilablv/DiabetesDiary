package com.ca.authentication

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class FirebaseAuthProvider(private val auth: FirebaseAuth) {

    val isUserSignedIn: Boolean
        get() = auth.currentUser != null

    val isAnonymousSignInMethod: Boolean
        get() = auth.currentUser?.isAnonymous == true

    fun signOut() = auth.signOut()

    fun signInAnonymously(
        onSuccess: (String?) -> Unit,
        onFailure: (Exception?) -> Unit
    ) {
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val id = task.result.user?.uid
                    onSuccess(id)
                } else {
                    onFailure(task.exception)
                }
            }
    }

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