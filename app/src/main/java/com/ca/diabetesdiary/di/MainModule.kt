package com.ca.diabetesdiary.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.ca.datastore.UserPreferences
import com.ca.diabetesdiary.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    fun provideMainRepository(
        userPreferencesDataStore: DataStore<UserPreferences>,
        @ApplicationContext context: Context
    ): MainRepository {
        return MainRepository(
            userPrefsStore = userPreferencesDataStore,
            context = context
        )
    }
}