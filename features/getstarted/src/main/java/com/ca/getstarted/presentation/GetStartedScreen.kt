package com.ca.getstarted.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ca.designsystem.theme.Theme

@Composable
fun GetStartedScreen() {

    Scaffold {
        Box(
            modifier = Modifier.padding(it)
        ) {
            Text(
                text = "Get Started",
                style = Theme.typography.headlineMedium,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}