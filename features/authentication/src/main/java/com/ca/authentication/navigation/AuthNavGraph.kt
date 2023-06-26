package com.ca.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ca.authentication.presentation.AuthScreen
import com.ca.onboarding.presentation.OnBoardingScreen

fun NavGraphBuilder.authNavGraph(
    route: String,
    onComplete: () -> Unit
) {
    navigation(
        startDestination = Route.Auth.route,
        route = route
    ) {

        composable(Route.OnBoarding.route) {
            OnBoardingScreen(
                onComplete
            )
        }
        composable(Route.Auth.route) {
            AuthScreen(
                onComplete = onComplete
            )
        }
    }
}