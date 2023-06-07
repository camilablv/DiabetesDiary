package com.ca.datastore.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.ca.diabetesdiary.UserPreferences
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object UserSerializer : Serializer<UserPreferences>{
    override val defaultValue: UserPreferences
        get() = UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferences {
        try {
            return UserPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    }

    override suspend fun writeTo(prefs: UserPreferences, output: OutputStream) {
        prefs.writeTo(output)
    }

}