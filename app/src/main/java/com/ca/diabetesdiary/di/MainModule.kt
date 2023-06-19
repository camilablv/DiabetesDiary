package com.ca.diabetesdiary.di

import androidx.datastore.core.DataStore
import com.ca.authentication.FirebaseAuthProvider
import com.ca.datastore.UserPreferences
import com.ca.diabetesdiary.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    fun provideMainRepository(
        userPreferencesDataStore: DataStore<UserPreferences>,
        authProvider: FirebaseAuthProvider
    ): MainRepository {
        return MainRepository(
            userPrefsStore = userPreferencesDataStore,
            authProvider = authProvider
        )
    }
}