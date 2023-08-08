package com.ca.designsystem.components.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.ca.designsystem.R
import com.ca.designsystem.theme.Theme
import com.ca.settings.presentation.components.SettingsSectionCard

@Composable
fun ThemeSection(
    isDarkThemeMode: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    SettingsSectionCard(
        modifier = Modifier,
        sectionTitle = "Theme"
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dark Mode",
                modifier = Modifier,
                style = Theme.typography.bodyLarge,
                color = Theme.colors.onBackground,
                textAlign = TextAlign.Center
            )

            Switch(
                checked = isDarkThemeMode,
                onCheckedChange = { onCheckedChange(it) },
                thumbContent = { Icon(painter = painterResource(id = R.drawable.bedtime), contentDescription = "") }
            )
        }
    }
}