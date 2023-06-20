package com.ca.settings.domain.model

import androidx.compose.ui.graphics.Color

data class Insulin(
    val name: String,
    val color: Color,
    val defaultDosage: Int
)
