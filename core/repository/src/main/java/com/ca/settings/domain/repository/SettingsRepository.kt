package com.ca.settings.domain.repository

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin

interface SettingsRepository {
    val isAnonymousSignInMethod: Boolean
    fun linkWithGoogleAccount(token: String, onSuccess: () -> Unit)
    suspend fun createSession(token: String)
    suspend fun updateGlucoseUnits(units: com.ca.model.GlucoseUnits): com.ca.model.GlucoseUnits
    suspend fun addInsulin(insulin: com.ca.model.Insulin): List<com.ca.model.Insulin>
    suspend fun updateInsulin(id: String, name: String, color: String, defaultDosage: Int)
    suspend fun deleteInsulin(id: String)
}