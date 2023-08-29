package com.ca.domain.model

import android.os.Parcelable
import java.time.LocalTime

interface ListItem : Parcelable {
    val time: LocalTime
}