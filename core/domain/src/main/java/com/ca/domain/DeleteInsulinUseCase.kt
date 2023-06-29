package com.ca.domain

import com.ca.model.Insulin
import com.ca.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class DeleteInsulinUseCase @Inject constructor(private val repository: SettingsRepository) {

    suspend operator fun invoke(id: String): List<Insulin> {
        return repository.deleteInsulin(id)
    }
}