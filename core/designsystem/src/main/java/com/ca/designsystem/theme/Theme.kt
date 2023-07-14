package com.ca.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun DiaryTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    CompositionLocalProvider(
        LocalDiaryColors provides colors,
        LocalDiaryShapes provides Shapes,
        LocalDiaryTypography provides Typography,
        LocalDiaryElevation provides Elevations,
        content = content
    )
}

object Theme {
    val colors: DiaryColors
        @Composable
        get() = LocalDiaryColors.current

    val shapes: DiaryShapes
        @Composable
        get() = LocalDiaryShapes.current

    val typography: DiaryTypography
        @Composable
        get() = LocalDiaryTypography.current

    val elevations: DiaryElevation
        @Composable
        get() = LocalDiaryElevation.current
}
