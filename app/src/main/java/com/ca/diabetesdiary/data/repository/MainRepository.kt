package com.ca.diabetesdiary.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import com.ca.datastore.UserPreferences
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val userPrefsStore: DataStore<UserPreferences>,
    context: Context
) {

    suspend fun updateId(id: String) {
        userPrefsStore.updateData {
            it.toBuilder().setId(id).build()
        }
    }
}