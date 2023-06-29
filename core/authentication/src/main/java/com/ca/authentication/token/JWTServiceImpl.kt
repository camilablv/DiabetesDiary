package com.ca.authentication.token

import com.ca.datastore.UserDataStore
import javax.inject.Inject

class JWTServiceImpl @Inject constructor(private val dataStore: UserDataStore) : JWTService {

    override suspend fun authToken(): String {
        return dataStore.authToken()
    }
}