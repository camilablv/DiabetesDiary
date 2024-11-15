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

val Blue100 = Color(0xFFBBDEFB)
val Green100 = Color(0xFFC8E6C9)
val Orange100 = Color(0xFFFFE0B2)
val Pink100 = Color(0xFFF8BBD0)
val Grey100 = Color(0xFFF5F5F5)
val Purple100 = Color(0xFFE1BEE7)

val Grey800 = Color(0xFF424242)
val Black = Color(0xFF000000)
val DeepPurple200 = Color(0xFFb39ddb)
val DarkRed = Color(0xFFCF6679)
val Grey50 = Color(0xFFFAFAFA)
val Grey900 = Color(0xFF212121)
val blueGrey600 = Color(0xFF546E7A)
val blueGrey400 = Color(0xFF78909C)

val DarkColors = DiaryColors(
    background = Black,
    primary = Purple40,
    secondary = DeepPurple200,
    surface = Grey800,
    surfaceVariant = blueGrey400,
    error = DarkRed,
    onBackground = Grey50,
    onPrimary = Color.White,
    onSecondary = Grey50,
    onSurface = Grey50,
    onSurfaceVariant = Color.Black,
    onError = Color.White
)

val LightColors = DiaryColors(
    background = Color.White,
    primary = Purple200,
    secondary = DeepPurple200,
    surface = Color.White,
    surfaceVariant = blueGrey600,
    error = Red,
    onBackground = Black,
    onPrimary = Grey50,
    onSecondary = Grey50,
    onSurface = Black,
    onSurfaceVariant = Color.White,
    onError = Grey50
)

@Immutable
data class DiaryColors(
    val background: Color,
    val primary: Color,
    val secondary: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val error:Color,
    val onBackground: Color,
    val onPrimary: Color,
    val onSecondary: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val onError: Color
)

val LocalDiaryColors = staticCompositionLocalOf<DiaryColors> {
    error("No DiaryColors Provided")
}