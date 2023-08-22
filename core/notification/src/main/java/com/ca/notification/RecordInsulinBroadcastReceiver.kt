package com.ca.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.ca.domain.repository.RecordInsulinRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@AndroidEntryPoint
class RecordInsulinBroadcastReceiver : BroadcastReceiver() {

    @Inject lateinit var repository: RecordInsulinRepository
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("RecordInsulinBroadcastReceiver", "onReceive")
        if (intent?.action == ACTION_INSULIN_TAKEN) {
            Log.d("RecordInsulinBroadcastReceiver", ACTION_INSULIN_TAKEN)
            val insulinId = intent.getStringExtra(INSULIN_ID_KEY)
            val dose = intent.getIntExtra(DOSE_KEY, 0)
            Log.d("RecordInsulinBroadcastReceiver", insulinId.toString())
            scope.launch {
                if (insulinId != null) {
                    repository.recordInsulin(insulinId, "", LocalDate.now(), LocalTime.now(), dose)
                }
            }
        }
    }

    companion object {
        const val ACTION_INSULIN_TAKEN = "insulin_taken"
        const val INSULIN_ID_KEY = "insulin_id_key"
        const val DOSE_KEY = "dose_key"
    }
}