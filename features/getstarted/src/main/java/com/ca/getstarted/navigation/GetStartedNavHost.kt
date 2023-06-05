package com.ca.getstarted.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ca.onboarding.presentation.OnBoardingScreen

@Composable
fun GetStartedNavHost(
    navController: NavHostController,
    onComplete: () -> Unit
) {
    NavHost(navController = navController, startDestination = Screen.OnBoarding.route) {
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(onComplete)
        }
        composable(Screen.Auth.route) {

        }
    }
}