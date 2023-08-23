package com.ca.alarmmanager.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ca.domain.repository.RemindersRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TimeChangedReceiver : BroadcastReceiver() {

    @Inject
    lateinit var remindersRepository: RemindersRepository
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    companion object {
        private const val ACTION_TIME_SET = "android.intent.action.TIME_SET"
    }

    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.action == ACTION_TIME_SET) {
            scope.launch {
                remindersRepository.rescheduleAll()
            }
        }
    }
}