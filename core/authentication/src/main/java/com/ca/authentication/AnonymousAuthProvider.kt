package com.ca.authentication

class AnonymousAuthProvider : AuthProvider() {

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