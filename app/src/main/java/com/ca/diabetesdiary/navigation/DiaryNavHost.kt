package com.ca.diabetesdiary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ca.authentication.navigation.authNavGraph
import com.ca.diabetesdiary.navigation.bottombar.BottomBarMenuNavHost
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
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(MainRoute.Home.route) {
            BottomBarMenuNavHost(
                navigateToRecordInsulinScreen = { navHostController.navigateToRecordInsulin() },
                navigateToRecordGlucoseScreen = { navHostController.navigateToRecordGlucose() }
            )
        }

        composable(MainRoute.OnBoarding.route) {
            OnBoardingScreen(
                navigateToHome = {
                    navHostController.navigate(MainRoute.Home.route) {
                        popUpTo(MainRoute.OnBoarding.route) { inclusive = true }
                    }
                }
            )
        }

        glucoseGraph(
            navigateBack = { navHostController.navigateBack() }
        )

        insulinGraph(
            navigateBack = { navHostController.navigateBack() }
        )

        authNavGraph(
            route = MainRoute.Auth.route,
            onComplete = {
                val nextDestination = if (shouldShowOnBoarding) MainRoute.OnBoarding.route else MainRoute.Home.route
                navHostController.navigate(nextDestination) {
                    popUpTo(MainRoute.Auth.route) { inclusive = true }
                }
            }
        )
    }
}