package com.ca.domain.usecase

import com.ca.domain.model.ListItem
import com.ca.domain.repository.RecordGlucoseRepository
import com.ca.domain.repository.RecordInsulinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.time.LocalDate
import javax.inject.Inject

class GetRecordsByDateUseCase @Inject constructor(
    private val recordInsulinRepository: RecordInsulinRepository,
    private val recordGlucoseRepository: RecordGlucoseRepository
) {
    suspend operator fun invoke(date: LocalDate): Flow<List<ListItem>> {
        val insulinRecords = recordInsulinRepository.recordsByDate(date)
        val glucoseRecords = recordGlucoseRepository.recordsByDate(date)
        return insulinRecords.combine(glucoseRecords) { insulinList, glucoseList ->
            insulinList + glucoseList
        }
    }
}