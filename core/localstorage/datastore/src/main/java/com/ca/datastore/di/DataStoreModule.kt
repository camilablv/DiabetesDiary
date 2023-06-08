package com.ca.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStoreFile
import com.ca.datastore.UserPreferences
import com.ca.datastore.data.UserSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

private const val DATA_STORE_FILE_NAME = "user_preferences.pb"

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    fun provideUserSerializer() = UserSerializer

    @Singleton
    @Provides
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context,
        serializer: Serializer<UserPreferences>
    ): DataStore<UserPreferences> {
        return DataStoreFactory.create(
            serializer = serializer,
            produceFile = { context.dataStoreFile(DATA_STORE_FILE_NAME) }
        )
    }
}