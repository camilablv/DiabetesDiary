package com.ca.domain.usecase

import com.ca.domain.repository.RemindersRepository
import com.ca.model.RecordGlucoseReminder
import javax.inject.Inject

class RemoveGlucoseReminderUseCase @Inject constructor(
    private val remindersRepository: RemindersRepository
) {

    suspend operator fun invoke(reminder: RecordGlucoseReminder) {
        remindersRepository.deleteGlucoseReminder(reminder)
    }
}