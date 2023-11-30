package com.ca.setlocalebottomsheet

import android.app.LocaleManager
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.platform.LocalContext
import com.ca.designsystem.components.BottomSheetContainer
import com.ca.designsystem.components.BottomSheetMenuOption

@Composable
fun SetLocaleBottomSheet(
    dismiss: () -> Unit
) {
    val localeManager = LocalContext.current.getSystemService(Context.LOCALE_SERVICE)

    BottomSheetContainer {
        Column {
            listOf("Українська", "English").forEach {
                BottomSheetMenuOption(title = it) {

                }
            }
        }
    }
}