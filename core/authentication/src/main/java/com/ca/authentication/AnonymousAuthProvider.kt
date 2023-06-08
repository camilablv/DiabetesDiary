package com.ca.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AnonymousAuthProvider {

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    val isUserSignedIn: Boolean
        get() = auth.currentUser != null

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
}