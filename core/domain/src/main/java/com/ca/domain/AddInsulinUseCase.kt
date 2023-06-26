package com.ca.domain

import com.ca.model.Insulin
import com.ca.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class AddInsulinUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(insulin: com.ca.model.Insulin): List<com.ca.model.Insulin> {
        return repository.addInsulin(insulin)
    }
}