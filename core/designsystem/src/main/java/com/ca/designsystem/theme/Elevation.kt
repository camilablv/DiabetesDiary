package com.ca.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val Elevations = DiaryElevation(
    default = 2.dp,
    pressed = 4.dp
)

@Immutable
data class DiaryElevation(
    val default: Dp,
    val pressed: Dp
)

val LocalDiaryElevation = staticCompositionLocalOf<DiaryElevation> {
    error("No DiaryElevation Provided")
}
