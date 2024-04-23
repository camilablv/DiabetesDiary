package com.ca.designsystem.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ca.designsystem.theme.Theme
import java.util.Locale

@Composable
fun SetLocaleDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    locales: Map<String, String>,
    selectedLocale: Locale,
    selectLocale: (Locale) -> Unit
) {
    if (!show) return
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = Theme.shapes.large,
            color = Theme.colors.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Select locale",
                    style = Theme.typography.headlineSmall,
                    color = Theme.colors.onSurface
                )
                Column {
                    locales.forEach {
                        Text(
                            modifier = Modifier
                                .background(
                                    color = if (it.key == selectedLocale.language) Theme.colors.secondary else Theme.colors.background,
                                    shape = Theme.shapes.medium
                                )
                                .padding(8.dp)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                ) { selectLocale(Locale(it.key)) },
                            text = it.value,
                            style = Theme.typography.headlineSmall,
                            color = if (it.key == selectedLocale.language) Theme.colors.onSecondary else Theme.colors.onBackground,
                        )
                    }
                }
            }
        }
    }
}