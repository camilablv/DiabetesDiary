package com.ca.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

internal class SettingsSerializer @Inject constructor() : Serializer<SettingsProto> {

    override val defaultValue: SettingsProto
        get() = SettingsProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SettingsProto {
        try {
            return SettingsProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: SettingsProto, output: OutputStream) {
        t.writeTo(output)
    }
}