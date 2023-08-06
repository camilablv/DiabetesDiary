package com.ca.domain.usecase

import com.ca.domain.repository.RemindersRepository
import com.ca.model.ListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetRemindersUseCase @Inject constructor(
    private val remindersRepository: RemindersRepository
) {

    suspend operator fun invoke(): Flow<List<ListItem>> {
        val glucoseReminders = remindersRepository.glucoseReminders()
        val insulinReminders = remindersRepository.insulinReminders()
        return glucoseReminders.combine(insulinReminders) { glucose, insulin ->
            glucose.plus(insulin)
        }
    }
}