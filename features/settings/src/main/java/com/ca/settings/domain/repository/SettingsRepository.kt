package com.ca.settings.domain.repository

import com.ca.settings.domain.model.GlucoseUnits

interface SettingsRepository {

    val isAnonymousSignInMethod: Boolean
    fun linkWithGoogleAccount(token: String, onSuccess: () -> Unit)
    suspend fun createSession(token: String)
    suspend fun updateGlucoseUnits(units: GlucoseUnits)
    suspend fun addInsulin(name: String, color: String, defaultDosage: Int)
    suspend fun updateInsulin(id: String, name: String, color: String, defaultDosage: Int)
    suspend fun deleteInsulin(id: String)
}