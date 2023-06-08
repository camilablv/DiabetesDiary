package com.ca.datastore.di

import androidx.datastore.core.Serializer
import com.ca.datastore.UserPreferences
import com.ca.datastore.data.UserSerializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SerializerModule {

    @Binds
    fun bindUserSerializer(serializer: UserSerializer): Serializer<UserPreferences>
}