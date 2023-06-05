package com.ca.recordglucose.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ca.designsystem.theme.Theme

@Composable
fun RecordGlucoseScreen() {

    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.Green)
        ) {
            Text(
                text = "Record Glucose",
                style = Theme.typography.headlineMedium,
                modifier = Modifier
                    .align(Alignment.Center),
                color = Color.Black
            )
        }
    }
} 