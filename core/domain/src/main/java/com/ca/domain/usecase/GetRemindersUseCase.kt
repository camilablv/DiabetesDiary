package com.ca.domain.usecase

import com.ca.domain.repository.RemindersRepository
import com.ca.model.Reminder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class GetRemindersUseCase @Inject constructor(
    private val remindersRepository: RemindersRepository
) {

    suspend operator fun invoke(): Flow<List<Reminder>> {
        val glucoseReminders = remindersRepository.glucoseReminders()
        val insulinReminders = remindersRepository.insulinReminders()
        return merge(glucoseReminders, insulinReminders)
    }
}