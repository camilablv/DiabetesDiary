package com.ca.domain.usecase

import com.ca.domain.repository.RecordInsulinRepository
import com.ca.model.InsulinRecord
import javax.inject.Inject

class RemoveInsulinRecordUseCase @Inject constructor(
    private val recordInsulinRepository: RecordInsulinRepository
) {

    suspend operator fun invoke(record: InsulinRecord) {
        recordInsulinRepository.delete(record)
    }
}