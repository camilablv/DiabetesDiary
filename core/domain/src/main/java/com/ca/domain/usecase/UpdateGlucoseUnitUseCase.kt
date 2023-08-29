package com.ca.domain.usecase

import com.ca.domain.model.GlucoseUnits
import com.ca.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateGlucoseUnitUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(unit: GlucoseUnits): GlucoseUnits? {
        return repository.updateGlucoseUnits(unit)
    }
}