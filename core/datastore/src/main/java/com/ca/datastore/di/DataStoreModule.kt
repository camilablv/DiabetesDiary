package com.ca.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStoreFile
import com.ca.datastore.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val USER_DATA_STORE_FILE_NAME = "user_preferences.pb"
private const val SETTINGS_DATA_STORE_FILE_NAME = "settings.pb"

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Singleton
    @Provides
    fun provideUserDataStoreAPIClass(dataStore: DataStore<UserPreferences>): UserDataStore {
        return UserDataStoreImpl(dataStore)
    }

    @Singleton
    @Provides
    fun provideSettingsDataStoreAPIClass(dataStore: DataStore<Settings>): SettingsDataStore {
        return SettingsDataStoreImpl(dataStore)
    }

    @Singleton
    @Provides
    fun provideUserSerializer(): Serializer<UserPreferences> = UserSerializer()

    @Singleton
    @Provides
    fun provideSettingsSerializer(): Serializer<Settings> = SettingsSerializer()

    @Singleton
    @Provides
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context,
        serializer: Serializer<UserPreferences>
    ): DataStore<UserPreferences> {
        return DataStoreFactory.create(
            serializer = serializer,
            produceFile = { context.dataStoreFile(USER_DATA_STORE_FILE_NAME) }
        )
    }

    @Singleton
    @Provides
    fun provideSettingsDataStore(
        @ApplicationContext context: Context,
        serializer: Serializer<Settings>
    ): DataStore<Settings> {
        return DataStoreFactory.create(
            serializer = serializer,
            produceFile = { context.dataStoreFile(SETTINGS_DATA_STORE_FILE_NAME) }
        )
    }
}