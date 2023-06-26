package com.ca.diabetesdiary.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ca.getstarted.navigation.getStartedNavGraph
import com.ca.home.navigation.BottomBarMenuNavHost
import com.ca.recordglucose.navigation.glucoseGraph
import com.ca.recordglucose.navigation.navigateToRecordGlucose
import com.ca.recordinsulin.navigation.insulinGraph
import com.ca.recordinsulin.navigation.navigateToRecordInsulin

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(Route.Home.route) {
            BottomBarMenuNavHost(
                navigateToRecordInsulinScreen = { navHostController.navigateToRecordInsulin()},
                navigateToRecordGlucoseScreen = { navHostController.navigateToRecordGlucose() }
            )
        }

        glucoseGraph()
        insulinGraph()

        getStartedNavGraph(
            navController = navHostController,
            route = Route.Auth.route,
            onComplete = {
                navHostController.navigate(Route.Home.route) {
                    popUpTo(Route.Auth.route) { inclusive = true }
                }
            },
            signInAnonymously = {  }
        )
    }
}