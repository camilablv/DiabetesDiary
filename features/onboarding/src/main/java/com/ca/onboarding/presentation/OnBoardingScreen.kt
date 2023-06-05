package com.ca.onboarding.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ca.designsystem.theme.Theme

@Composable
fun OnBoardingScreen(
    toHome: () -> Unit
) {

    Scaffold {
        Box(
            modifier = Modifier.padding(it)
        ) {
            Text(
                text = "On Boarding",
                style = Theme.typography.headlineMedium,
                modifier = Modifier
                    .align(Alignment.Center)
            )
            Button(onClick = { toHome() }) {
                Text(
                    text = "To Home",
                    style = Theme.typography.headlineMedium,
                    modifier = Modifier
                )
            }
        }
    }
}