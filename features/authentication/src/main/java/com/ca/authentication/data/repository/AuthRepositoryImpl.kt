package com.ca.authentication.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.ca.authentication.FirebaseAuthProvider
import com.ca.authentication.domain.repository.AuthRepository
import com.ca.datastore.UserPreferences
import com.ca.network.api.NetworkClient
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authProvider: FirebaseAuthProvider,
    private val dataStore: DataStore<UserPreferences>,
    private val networkClient: NetworkClient
    ) : AuthRepository {

    override suspend fun createSession(idToken: String, onSuccess: suspend () -> Unit) {
        networkClient.createSession(idToken).onSuccess { data ->
            onSuccess()

            data.session.let { session ->
                Log.d("AuthRepositoryImpl", session.token)
                dataStore.updateData { prefs ->
                    prefs.toBuilder()
                        .setAuthToken(session.token)
                        .setEmail(session.user.email)
                        .build()
                }
            }
        }
    }

    override suspend fun signInWithGoogle(token: String) {
        authProvider.signInWithGoogle(token)
    }
}