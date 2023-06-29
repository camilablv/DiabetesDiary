package com.ca.datastore

interface UserDataStore {
    suspend fun updateUserData(token: String?, email: String?)
    suspend fun authToken(): String
}