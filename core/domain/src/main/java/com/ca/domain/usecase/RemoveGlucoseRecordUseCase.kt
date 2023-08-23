package com.ca.domain.usecase

import com.ca.domain.model.GlucoseRecord
import com.ca.domain.repository.RecordGlucoseRepository
import javax.inject.Inject

class RemoveGlucoseRecordUseCase @Inject constructor(
    private val recordGlucoseRepository: RecordGlucoseRepository
) {

    suspend operator fun invoke(record: GlucoseRecord) {
        recordGlucoseRepository.delete(record)
    }
}