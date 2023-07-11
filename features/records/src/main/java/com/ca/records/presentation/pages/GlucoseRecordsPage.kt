package com.ca.records.presentation.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ca.designsystem.theme.Theme

@Composable
fun GlucoseRecordsPage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Glucose Records",
            modifier = Modifier
                .align(Alignment.Center),
            style = Theme.typography.headlineLarge
        )
    }
}