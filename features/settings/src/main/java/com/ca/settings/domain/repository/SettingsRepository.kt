package com.ca.settings.domain.repository

import com.ca.data.model.GlucoseUnits
import com.ca.data.model.Insulin

interface SettingsRepository {

    val isAnonymousSignInMethod: Boolean
    fun linkWithGoogleAccount(token: String, onSuccess: () -> Unit)
    suspend fun createSession(token: String)
    suspend fun updateGlucoseUnits(units: GlucoseUnits)
    suspend fun addInsulin(insulin: Insulin)
    suspend fun updateInsulin(id: String, name: String, color: String, defaultDosage: Int)
    suspend fun deleteInsulin(id: String)
}