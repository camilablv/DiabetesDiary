package com.ca.domain.usecase

import com.ca.domain.repository.SettingsRepository
import com.ca.model.Insulin
import javax.inject.Inject

class DeleteInsulinUseCase @Inject constructor(private val repository: SettingsRepository) {

    suspend operator fun invoke(id: String): List<Insulin> {
        repository.deleteInsulin(id)
        return listOf()
    }
}