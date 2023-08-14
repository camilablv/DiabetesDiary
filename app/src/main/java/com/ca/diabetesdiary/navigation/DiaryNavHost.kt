package com.ca.diabetesdiary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ca.authentication.navigation.authNavGraph
import com.ca.designsystem.components.RecordsMenuBottomSheet
import com.ca.diabetesdiary.navigation.bottombar.BottomBarMenuNavHost
import com.ca.glucosereminder.navigation.glucoseReminderGraph
import com.ca.insulinreminder.navigation.insulinReminderGraph
import com.ca.onboarding.presentation.OnBoardingScreen
import com.ca.recordglucose.navigation.glucoseGraph
import com.ca.recordinsulin.navigation.insulinGraph
import com.ca.settings.presentation.SettingsScreen
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet

fun NavController.navigateBack() {
    popBackStack()
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun MainNavHost(
    navHostController: NavHostController,
    startDestination: String,
    shouldShowOnBoarding: Boolean,
    modifier: Modifier,
    bottomSheetNavigator: BottomSheetNavigator
) {
    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
        NavHost(
            navController = navHostController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            composable(MainRoute.Home.route) {
                BottomBarMenuNavHost(navHostController)
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

            composable(MainRoute.Settings.route) {
                SettingsScreen(
                    navigateBack = { navHostController.navigateBack() }
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

            insulinReminderGraph(
                navigateBack = { navHostController.navigateBack() }
            )

            glucoseReminderGraph(
                navigateBack = { navHostController.navigateBack() }
            )

            bottomSheet("sheet") {
                RecordsMenuBottomSheet()
            }
        }
    }

}