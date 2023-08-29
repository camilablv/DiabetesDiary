package com.ca.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Insulin(
    val id: String,
    val name: String,
    val color: String,
    val defaultDose: Int
) : Parcelable
