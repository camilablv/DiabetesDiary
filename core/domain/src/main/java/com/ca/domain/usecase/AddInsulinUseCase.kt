package com.ca.domain.usecase

import com.ca.domain.repository.SettingsRepository
import com.ca.model.Insulin
import javax.inject.Inject

class AddInsulinUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(name: String, color: String, defaultDose: Int): List<Insulin>? {
        if (name.isEmpty()) return null
        return repository.addInsulin(name, color, defaultDose)
    }
}