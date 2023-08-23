package com.ca.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalTime

@Parcelize
data class RecordGlucoseReminder(
    val id: Int = 0,
    override val time: LocalTime,
    val iteration: ReminderIteration,
    val enabled: Boolean,
): ListItem, Parcelable
