package com.ca.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class UserDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<UserPreferences>
) : UserDataStore {

    override suspend fun updateUserData(token: String?, email: String?) {
        dataStore.updateData {
            it.toBuilder()
                .setAuthToken(token)
                .setEmail(email)
                .build()
        }
    }

    override suspend fun authToken(): String {
        return dataStore.data.first().authToken
    }
}