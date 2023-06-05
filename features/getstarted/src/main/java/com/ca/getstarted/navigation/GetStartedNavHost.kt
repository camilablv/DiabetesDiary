package com.ca.getstarted.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ca.getstarted.presentation.GetStartedScreen
import com.ca.onboarding.presentation.OnBoardingScreen

@Composable
fun GetStartedNavHost(
    navController: NavHostController,
    onComplete: () -> Unit
) {
    NavHost(navController = navController, startDestination = Route.GetStarted.route) {
        composable(Route.GetStarted.route) {
            GetStartedScreen(
                navigateToOnBoardingScreen = { navController.navigate(Route.OnBoarding.route) },
                navigateToSignInScreen = { navController.navigate(Route.Auth.route) }
            )
        }
        composable(Route.OnBoarding.route) {
            OnBoardingScreen(onComplete)

        }
        composable(Route.Auth.route) {

        }
    }
}