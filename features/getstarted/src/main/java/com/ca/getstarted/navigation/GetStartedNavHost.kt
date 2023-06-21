package com.ca.getstarted.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ca.authentication.presentation.AuthScreen
import com.ca.getstarted.presentation.WelcomeScreen
import com.ca.onboarding.presentation.OnBoardingScreen

@Composable
fun GetStartedNavHost(
    navController: NavHostController,
    onComplete: () -> Unit,
    signInAnonymously: () -> Unit
) {
    NavHost(navController = navController, startDestination = Route.OnBoarding.route) {
        composable(Route.Welcome.route) {
            WelcomeScreen(
                navigateToOnBoardingScreen = { navController.navigate(Route.OnBoarding.route) },
                navigateToAuthScreen = { navController.navigate(Route.Auth.route) }
            )
        }
        composable(Route.OnBoarding.route) {
            OnBoardingScreen(
                onComplete,
                signInAnonymously
            )
        }
        composable(Route.Auth.route) {
            AuthScreen(
                onComplete = onComplete
            )
        }
    }
}