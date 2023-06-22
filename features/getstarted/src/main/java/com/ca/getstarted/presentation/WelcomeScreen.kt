package com.ca.getstarted.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ca.designsystem.theme.Theme

@Composable
fun WelcomeScreen(
    navigateToOnBoardingScreen: () -> Unit,
    navigateToAuthScreen: () -> Unit
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
                text = "Welcome",
                style = Theme.typography.headlineMedium,
                modifier = Modifier,
                color = Color.Black
            )
            Button(onClick = { navigateToOnBoardingScreen() }) {
                Text("OnBoarding")
            }
            Button(onClick = { navigateToAuthScreen() }) {
                Text("Sign In With Google")
            }
        }
    }
}