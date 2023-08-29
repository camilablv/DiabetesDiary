package com.ca.model

import kotlinx.android.parcel.Parcelize
import java.time.LocalTime

@Parcelize
data class RecordInsulinReminder(
    val id: Int,
    override val time: LocalTime,
    val iteration: ReminderIteration,
    val insulinId: String,
    val dose: Int,
    val enabled: Boolean,
    val insulin: Insulin? = null
): ListItem, Parcelable {
    val isActive = enabled && time > LocalTime.now()
}