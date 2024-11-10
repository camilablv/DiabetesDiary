package com.ca.diabetesdiary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ca.authentication.navigation.AuthGraph
import com.ca.authentication.navigation.authNavGraph
import com.ca.diabetesdiary.navigation.bottombar.BottomBarMenuNavHost
import com.ca.editglucoserecordbottomsheet.navigation.navigateToGlucoseRecordBottomSheet
import com.ca.editglucosereminderbottomsheet.navigation.navigateToGlucoseReminderBottomSheet
import com.ca.editinsulinrecordbottomsheet.navigation.navigateToInsulinRecordBottomSheet
import com.ca.editinsulinreminderbottomsheet.navigation.navigateToInsulinReminderBottomSheet
import com.ca.glucosereminder.navigation.glucoseReminderGraph
import com.ca.insulinreminder.navigation.insulinReminderGraph
import com.ca.model.TopLevelDestination
import com.ca.onboarding.presentation.OnBoardingScreen
import com.ca.recordglucose.navigation.glucoseGraph
import com.ca.recordinsulin.navigation.insulinGraph
import com.ca.settings.presentation.SettingsScreen
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

fun NavController.navigateBack() {
    popBackStack()
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun MainNavHost(
    navHostController: NavHostController,
    startDestination: TopLevelDestination,
    shouldShowOnBoarding: Boolean,
    modifier: Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<MainGraph.Home> {
            BottomBarMenuNavHost(
                mainNavController = navHostController,
                openRecordsMenuBottomSheet = { navHostController.navigate("records_menu") },
                openInsulinRecordBottomSheet = {
                    navHostController.navigateToInsulinRecordBottomSheet(it)
                },
                openInsulinReminderBottomSheet = {
                    navHostController.navigateToInsulinReminderBottomSheet(it)
                },
                openGlucoseRecordBottomSheet = {
                    navHostController.navigateToGlucoseRecordBottomSheet(it)
                },
                openGlucoseReminderBottomSheet = {
                    navHostController.navigateToGlucoseReminderBottomSheet(it)
                }
            )
        }

        composable<MainGraph.OnBoarding> {
            OnBoardingScreen(
                navigateToHome = {
                    navHostController.navigate(MainGraph.Home) {
                        popUpTo(MainGraph.OnBoarding) { inclusive = true }
                    }
                }
            )
        }

        composable<MainGraph.Settings> {
            SettingsScreen(
                navigateBack = { navHostController.navigateBack() }
            )
        }

        glucoseGraph(
            navigateBack = { navHostController.navigateBack() }
        )

        insulinGraph(
            navigateBack = { navHostController.navigateBack() },
            navigateToSettings = { navHostController.navigate(MainGraph.Settings) }
        )

        authNavGraph(
            onComplete = {
                val nextDestination = if (shouldShowOnBoarding) MainGraph.OnBoarding else MainGraph.Home
                navHostController.navigate(nextDestination) {
                    popUpTo(AuthGraph.Login) { inclusive = true }
                }
            }
        )

        insulinReminderGraph(
            navigateBack = { navHostController.navigateBack() },
            navigateToSettings = { navHostController.navigate(MainGraph.Settings) }
        )

        glucoseReminderGraph(
            navigateBack = { navHostController.navigateBack() }
        )

//        bottomSheet(route = "records_menu") {
//            RecordsMenuBottomSheet(
//                navigateToRecordGlucose = { navHostController.navigateToRecordGlucose() },
//                navigateToRecordInsulin = { navHostController.navigateToRecordInsulin() },
//            )
//        }

//        editInsulinRecordBottomSheet(
//            navigateToEditInsulinRecord = { navHostController.navigateToRecordInsulin(it) },
//            dismiss = { navHostController.navigateBack() }
//        )
//
//        editInsulinReminderBottomSheet(
//            navigateToEditInsulinReminder = { navHostController.navigateToInsulinReminder(it) },
//            dismiss = { navHostController.navigateBack() }
//        )
//
//        editGlucoseRecordBottomSheet(
//            navigateToEditGlucoseRecord = { navHostController.navigateToRecordGlucose(it) },
//            dismiss = { navHostController.navigateBack() }
//        )
//
//        editGlucoseReminderBottomSheet(
//            navigateToEditGlucoseReminder = { navHostController.navigateToGlucoseReminder(it) },
//            dismiss = { navHostController.navigateBack() }
//        )
    }
}