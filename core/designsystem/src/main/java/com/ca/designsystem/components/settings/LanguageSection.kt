package com.ca.designsystem.components.settings

import androidx.compose.foundation.clickable
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
fun LanguageSection(
    selectedLanguage: String,
    onClick: () -> Unit
) {
    SettingsSectionCard(
        modifier = Modifier,
        sectionTitle = "Language"
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Language",
                modifier = Modifier,
                style = Theme.typography.bodyLarge,
                color = Theme.colors.onBackground,
                textAlign = TextAlign.Center
            )

            Text(
                text = selectedLanguage,
                modifier = Modifier,
                style = Theme.typography.bodyLarge,
                color = Theme.colors.onBackground,
                textAlign = TextAlign.Center
            )
        }
    }
}