package com.ca.domain.usecase

import com.ca.domain.repository.RecordGlucoseRepository
import com.ca.domain.repository.RecordInsulinRepository
import com.ca.model.Record
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.time.LocalDate
import javax.inject.Inject

class GetRecordsByDateUseCase @Inject constructor(
    private val recordInsulinRepository: RecordInsulinRepository,
    private val recordGlucoseRepository: RecordGlucoseRepository
) {
    suspend operator fun invoke(date: LocalDate): Flow<List<Record>> {
        val insulinRecords = recordInsulinRepository.recordsByDate(date)
        val glucoseRecords = recordGlucoseRepository.recordsByDate(date)
        return insulinRecords.combine(glucoseRecords) { insulinList, glucoseList ->
            insulinList.plus(glucoseList)
        }
    }
}