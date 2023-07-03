package com.ca.domain

import com.ca.model.GlucoseUnits
import com.ca.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateGlucoseUnitUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(unit: GlucoseUnits): GlucoseUnits? {
        return repository.updateGlucoseUnits(unit)
    }
}