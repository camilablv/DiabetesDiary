package com.ca.domain.usecase

import com.ca.domain.repository.SettingsRepository
import com.ca.model.GlucoseUnits
import javax.inject.Inject

class UpdateGlucoseUnitUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(unit: GlucoseUnits): GlucoseUnits? {
        return repository.updateGlucoseUnits(unit)
    }
}