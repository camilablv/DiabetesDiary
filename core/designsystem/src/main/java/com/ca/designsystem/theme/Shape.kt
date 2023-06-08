package com.ca.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

val Shapes = DiaryShapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

@Immutable
data class DiaryShapes(
    val small: Shape,
    val medium: Shape,
    val large: Shape
)

val LocalDiaryShapes = staticCompositionLocalOf<DiaryShapes> {
    error("No DiaryShapes Provided")
}