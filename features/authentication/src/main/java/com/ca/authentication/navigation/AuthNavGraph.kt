package com.ca.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ca.authentication.presentation.AuthScreen
import com.ca.onboarding.presentation.OnBoardingScreen

fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController,
    route: String,
    navigateToHome: () -> Unit,
    isOnBoardingShowed: Boolean
) {
    navigation(
        startDestination = Route.Auth.route,
        route = route
    ) {

        composable(Route.OnBoarding.route) {
            OnBoardingScreen(
                navigateToHome
            )
        }
        composable(Route.Auth.route) {
            AuthScreen(
                onComplete = {
                    if (isOnBoardingShowed) {
                        navigateToHome()
                    } else {
                        navHostController.navigate(Route.OnBoarding.route)
                    }
                }
            )
        }
    }
}