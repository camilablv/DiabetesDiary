package com.ca.domain.usecase

import com.ca.domain.model.RecordInsulinReminder
import com.ca.domain.repository.RecordInsulinRepository
import com.ca.domain.repository.RemindersRepository
import java.time.LocalDate
import javax.inject.Inject

class MarkInsulinReminderAsDoneUseCase @Inject constructor(
    private val recordInsulinRepository: RecordInsulinRepository,
    private val reminderRepository: RemindersRepository
) {

    suspend operator fun invoke(reminder: RecordInsulinReminder) {
        recordInsulinRepository.createRecord(
            reminder.insulinId,
            "",
            LocalDate.now(),
            reminder.time,
            reminder.dose
        )
        reminderRepository.deleteInsulinReminder(reminder)
    }
}