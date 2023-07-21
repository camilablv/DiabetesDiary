package com.ca.domain.usecase

import com.ca.model.Insulin
import com.ca.domain.repository.SettingsRepository
import javax.inject.Inject

class DeleteInsulinUseCase @Inject constructor(private val repository: SettingsRepository) {

    suspend operator fun invoke(id: String): List<Insulin> {
        return repository.deleteInsulin(id)
    }
}