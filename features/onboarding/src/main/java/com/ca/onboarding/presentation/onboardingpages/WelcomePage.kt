package com.ca.onboarding.presentation.onboardingpages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ca.designsystem.theme.Theme

@Composable
fun WelcomePage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Welcome!",
            style = Theme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}