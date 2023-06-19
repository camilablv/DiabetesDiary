package com.ca.diabetesdiary.data.repository

import androidx.datastore.core.DataStore
import com.ca.authentication.FirebaseAuthProvider
import com.ca.datastore.UserPreferences
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val userPrefsStore: DataStore<UserPreferences>,
    private val authProvider: FirebaseAuthProvider
) {

    val isUserSignedIn: Boolean
        get() = authProvider.isUserSignedIn

    fun signInAnonymously() {
        authProvider.signInAnonymously(
            onSuccess = { id ->
                flow<Unit> {
                    coroutineScope { if (id != null) updateUserId(id) }
                }
            },
            onFailure = {}
        )
    }

    private suspend fun updateUserId(id: String) {
        userPrefsStore.updateData {
            it.toBuilder().setId(id).build()
        }
    }
}