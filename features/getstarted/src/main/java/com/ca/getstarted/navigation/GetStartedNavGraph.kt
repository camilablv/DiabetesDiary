package com.ca.getstarted.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ca.authentication.presentation.AuthScreen
import com.ca.onboarding.presentation.OnBoardingScreen

fun NavGraphBuilder.getStartedNavGraph(
    navController: NavHostController,
    route: String,
    onComplete: () -> Unit,
    signInAnonymously: () -> Unit
) {
    navigation(
        startDestination = Route.Auth.route,
        route = route
    ) {

        composable(Route.OnBoarding.route) {
            OnBoardingScreen(
                onComplete ,
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
