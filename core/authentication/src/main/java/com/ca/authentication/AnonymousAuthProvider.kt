package com.ca.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AnonymousAuthProvider {

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    fun signInAnonymously() {
        auth.signInAnonymously()
            .addOnCompleteListener { task ->

            }
    }
}