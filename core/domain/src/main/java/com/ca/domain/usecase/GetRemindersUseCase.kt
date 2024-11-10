package com.ca.domain.usecase

import com.ca.domain.repository.RemindersRepository
import com.ca.domain.repository.SettingsRepository
import com.ca.model.RecordInsulinReminder
import com.ca.model.Reminder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetRemindersUseCase @Inject constructor(
    private val remindersRepository: RemindersRepository,
    private val settingsRepository: SettingsRepository,
) {

    suspend operator fun invoke(): Flow<List<Reminder>> {
        val glucoseReminders = remindersRepository.glucoseReminders()
        val insulinReminders = remindersRepository.insulinReminders()
        val insulinList = settingsRepository.insulins()
        return glucoseReminders.combine(insulinReminders) { glucose, insulin ->
            glucose.plus(insulin).map { reminder ->
                if (reminder is RecordInsulinReminder)
                    reminder.copy(insulin = insulinList.find { it.id == reminder.insulinId })
                else reminder
            }
        }
    }
}