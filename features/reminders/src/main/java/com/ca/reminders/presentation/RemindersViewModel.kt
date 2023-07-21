package com.ca.reminders.presentation

import androidx.lifecycle.ViewModel
import com.ca.domain.repository.InsulinReminderRepository
import javax.inject.Inject

class RemindersViewModel @Inject constructor(
    private val repository: InsulinReminderRepository
) : ViewModel() {

    init {
        repository
    }
}