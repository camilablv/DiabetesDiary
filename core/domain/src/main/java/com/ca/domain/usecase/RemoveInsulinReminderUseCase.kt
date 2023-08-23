package com.ca.domain.usecase

import com.ca.domain.model.RecordInsulinReminder
import com.ca.domain.repository.RemindersRepository
import javax.inject.Inject

class RemoveInsulinReminderUseCase @Inject constructor(
    private val remindersRepository: RemindersRepository
) {

    suspend operator fun invoke(reminder: RecordInsulinReminder) {
        remindersRepository.deleteInsulinReminder(reminder)
    }
}