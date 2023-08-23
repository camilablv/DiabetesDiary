package com.ca.notification

import androidx.core.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.ca.domain.repository.RecordGlucoseRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@AndroidEntryPoint
class GlucoseMeasuringInputReceiver : BroadcastReceiver() {

    @Inject lateinit var glucoseRepository: RecordGlucoseRepository
    @Inject lateinit var notificationManager: DiaryNotificationManager
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.action != ACTION_GLUCOSE_MEASURED) return
        val text = RemoteInput.getResultsFromIntent(intent)?.getCharSequence(INPUT_TEXT_KEY)
        Log.d("GlucoseMeasuringInputReceiver", text.toString())
    }

    companion object {
        const val NOTIFICATION_ID = "notification_id"
        const val ACTION_GLUCOSE_MEASURED = "insulin_taken"
        const val INPUT_TEXT_KEY = "input_text_key"
    }
}