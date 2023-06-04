package com.ca.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Grey800 = Color(0xFF424242)
val Black = Color(0xFF000000)
val DeepPurple200 = Color(0xFFb39ddb)
val DarkRed = Color(0xFFCF6679)
val Grey50 = Color(0xFFFAFAFA)
val Grey900 = Color(0xFF212121)

val DarkColors = DiaryColors(
    background = Grey900,
    primary = Purple40,
    secondary = DeepPurple200,
    surface = Grey800,
    error = DarkRed,
    onBackground = Grey50,
    onPrimary = Grey50,
    onSecondary = Grey800,
    onSurface = Grey50,
    onError = Grey50
)

val LightColors = DiaryColors(
    background = Color.White,
    primary = Purple200,
    secondary = DeepPurple200,
    surface = Color.White,
    error = Red,
    onBackground = Black,
    onPrimary = Color.White,
    onSecondary = Black,
    onSurface = Black,
    onError = Color.White
)

@Immutable
data class DiaryColors(
    val background: Color,
    val primary: Color,
    val secondary: Color,
    val surface: Color,
    val error:Color,
    val onBackground: Color,
    val onPrimary: Color,
    val onSecondary: Color,
    val onSurface: Color,
    val onError: Color
)

val LocalDiaryColors = staticCompositionLocalOf<DiaryColors> {
    error("No DiaryColors Provided")
}