package com.ca.diabetesdiary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ca.authentication.navigation.authNavGraph
import com.ca.home.navigation.BottomBarMenuNavHost
import com.ca.onboarding.presentation.OnBoardingScreen
import com.ca.recordglucose.navigation.glucoseGraph
import com.ca.recordglucose.navigation.navigateToRecordGlucose
import com.ca.recordinsulin.navigation.insulinGraph
import com.ca.recordinsulin.navigation.navigateToRecordInsulin

fun NavController.navigateBack() {
    popBackStack()
}

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    startDestination: String,
    shouldShowOnBoarding: Boolean,
    modifier: Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = "insulin_graph",
        modifier = modifier
    ) {
        composable(Route.Home.route) {
            BottomBarMenuNavHost(
                navigateToRecordInsulinScreen = { navHostController.navigateToRecordInsulin() },
                navigateToRecordGlucoseScreen = { navHostController.navigateToRecordGlucose() }
            )
        }

        composable(Route.OnBoarding.route) {
            OnBoardingScreen(
                navigateToHome = {
                    navHostController.navigate(Route.Home.route) {
                        popUpTo(Route.OnBoarding.route) { inclusive = true }
                    }
                }
            )
        }

        glucoseGraph()

        insulinGraph(
            navigateBack = { navHostController.navigateBack() }
        )

        authNavGraph(
            route = Route.Auth.route,
            onComplete = {
                val nextDestination = if (shouldShowOnBoarding) Route.OnBoarding.route else Route.Home.route
                navHostController.navigate(nextDestination) {
                    popUpTo(Route.Auth.route) { inclusive = true }
                }
            }
        )
    }
}