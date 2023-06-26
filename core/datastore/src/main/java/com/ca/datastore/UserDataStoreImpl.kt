package com.ca.datastore

import androidx.datastore.core.DataStore
import javax.inject.Inject

class UserDataStoreImpl @Inject constructor(
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
}