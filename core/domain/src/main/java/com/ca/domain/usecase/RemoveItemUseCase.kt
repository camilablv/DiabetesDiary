package com.ca.domain.usecase

import com.ca.domain.repository.RecordGlucoseRepository
import com.ca.domain.repository.RecordInsulinRepository
import com.ca.domain.repository.RemindersRepository
import com.ca.model.*
import javax.inject.Inject

class RemoveItemUseCase @Inject constructor(
    private val recordGlucoseRepository: RecordGlucoseRepository,
    private val recordInsulinRepository: RecordInsulinRepository,
    private val remindersRepository: RemindersRepository,
) {

    suspend operator fun invoke(item: ListItem) {
        when(item) {
            is RecordInsulinReminder -> { remindersRepository.deleteInsulinReminder(item) }
            is RecordGlucoseReminder -> { remindersRepository.deleteGlucoseReminder(item) }
            is InsulinRecord -> { recordInsulinRepository.delete(item) }
            is GlucoseRecord -> { recordGlucoseRepository.delete(item) }
        }
    }
}