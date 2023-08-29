package com.ca.diabetesdiary.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ca.authentication.navigation.authNavGraph
import com.ca.designsystem.components.RecordsMenuBottomSheet
import com.ca.designsystem.theme.Theme
import com.ca.diabetesdiary.navigation.bottombar.BottomBarMenuNavHost
import com.ca.editglucoserecordbottomsheet.navigation.editGlucoseRecordBottomSheet
import com.ca.editglucoserecordbottomsheet.navigation.navigateToGlucoseRecordBottomSheet
import com.ca.editglucosereminderbottomsheet.navigation.editGlucoseReminderBottomSheet
import com.ca.editglucosereminderbottomsheet.navigation.navigateToGlucoseReminderBottomSheet
import com.ca.editinsulinrecordbottomsheet.navigation.editInsulinRecordBottomSheet
import com.ca.editinsulinrecordbottomsheet.navigation.navigateToInsulinRecordBottomSheet
import com.ca.editinsulinreminderbottomsheet.navigation.editInsulinReminderBottomSheet
import com.ca.editinsulinreminderbottomsheet.navigation.navigateToInsulinReminderBottomSheet
import com.ca.glucosereminder.navigation.glucoseReminderGraph
import com.ca.glucosereminder.navigation.navigateToGlucoseReminder
import com.ca.insulinreminder.navigation.insulinReminderGraph
import com.ca.insulinreminder.navigation.navigateToInsulinReminder
import com.ca.onboarding.presentation.OnBoardingScreen
import com.ca.recordglucose.navigation.glucoseGraph
import com.ca.recordglucose.navigation.navigateToRecordGlucose
import com.ca.recordinsulin.navigation.insulinGraph
import com.ca.recordinsulin.navigation.navigateToRecordInsulin
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
    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = Theme.shapes.bottomSheet
    ) {
        NavHost(
            navController = navHostController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            composable(MainRoute.Home.route) {
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
                navigateBack = { navHostController.navigateBack() },
                navigateToSettings = { navHostController.navigate(MainRoute.Settings.route) }
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
                navigateBack = { navHostController.navigateBack() },
                navigateToSettings = { navHostController.navigate(MainRoute.Settings.route) }
            )

            glucoseReminderGraph(
                navigateBack = { navHostController.navigateBack() }
            )

            bottomSheet(route = "records_menu") {
                RecordsMenuBottomSheet(
                    navigateToRecordGlucose = { navHostController.navigateToRecordGlucose() },
                    navigateToRecordInsulin = { navHostController.navigateToRecordInsulin() },
                )
            }

            editInsulinRecordBottomSheet(
                navigateToEditInsulinRecord = { navHostController.navigateToRecordInsulin(it) },
                dismiss = { navHostController.navigateBack() }
            )

            editInsulinReminderBottomSheet(
                navigateToEditInsulinReminder = { navHostController.navigateToInsulinReminder(it) },
                dismiss = { navHostController.navigateBack() }
            )

            editGlucoseRecordBottomSheet(
                navigateToEditGlucoseRecord = { navHostController.navigateToRecordGlucose(it) },
                dismiss = { navHostController.navigateBack() }
            )

            editGlucoseReminderBottomSheet(
                navigateToEditGlucoseReminder = { navHostController.navigateToGlucoseReminder(it) },
                dismiss = { navHostController.navigateBack() }
            )
        }
    }
}