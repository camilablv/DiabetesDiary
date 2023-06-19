package com.ca.authentication.data.repository

import androidx.datastore.core.DataStore
import com.ca.authentication.FirebaseAuthProvider
import com.ca.authentication.domain.repository.AuthRepository
import com.ca.datastore.UserPreferences
import com.ca.network.api.NetworkClient
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authProvider: FirebaseAuthProvider,
    private val userPrefsStore: DataStore<UserPreferences>,
    private val networkClient: NetworkClient
    ) : AuthRepository {

    override suspend fun createUser(idToken: String) {
        networkClient.createUser(idToken)
    }

    override suspend fun signInWithGoogle(token: String) {
        authProvider.signInWithGoogle(token)
        saveToken(token)
    }

    override suspend fun saveToken(token: String) {
        userPrefsStore.updateData {
            it.toBuilder().setAuthToken(token).build()
        }
    }
}