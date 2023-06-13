package com.ca.authentication.data.repository

import androidx.datastore.core.DataStore
import com.ca.authentication.domain.repository.AuthRepository
import com.ca.datastore.UserPreferences
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userPrefsStore: DataStore<UserPreferences>
    ) : AuthRepository {

    override suspend fun saveToken(token: String) {
        userPrefsStore.updateData {
            it.toBuilder().setAuthToken(token).build()
        }
    }
}