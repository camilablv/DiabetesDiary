package com.ca.onboarding.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ca.designsystem.theme.Theme

@Composable
fun OnBoardingScreen(
    toHome: () -> Unit,
    signInAnonymously: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "On Boarding",
                style = Theme.typography.headlineMedium,
                modifier = Modifier,
                color = Theme.colors.onBackground
            )
            Button(
                onClick = {
                    signInAnonymously()
                    toHome()
                }
            ) {
                Text(
                    text = "To Home",
                    style = Theme.typography.headlineMedium,
                    modifier = Modifier
                )
            }
        }
    }
}