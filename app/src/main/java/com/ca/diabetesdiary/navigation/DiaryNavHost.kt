package com.ca.diabetesdiary.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ca.authentication.navigation.authNavGraph
import com.ca.designsystem.components.RecordsMenuBottomSheet
import com.ca.designsystem.theme.Theme
import com.ca.diabetesdiary.navigation.bottombar.BottomBarMenuNavHost
import com.ca.diabetesdiary.navigation.bottomsheet.*
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
                    openRecordsMenuBottomSheet = { navHostController.navigate(BottomSheetRoute.RecordsMenu.route) },
                    openInsulinRecordBottomSheet = {
                        navHostController.navigate("${BottomSheetRoute.EditInsulinRecord.route}/$it")
                    },
                    openInsulinReminderBottomSheet = {
                        navHostController.navigate("${BottomSheetRoute.EditInsulinReminder.route}/$it")
                    },
                    openGlucoseRecordBottomSheet = {
                        navHostController.navigate("${BottomSheetRoute.EditGlucoseRecord.route}/$it")
                    },
                    openGlucoseReminderBottomSheet = {
                        navHostController.navigate("${BottomSheetRoute.EditGlucoseReminder.route}/$it")
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

            bottomSheet(BottomSheetRoute.RecordsMenu.route) {
                RecordsMenuBottomSheet(
                    navigateToRecordGlucose = { navHostController.navigateToRecordGlucose() },
                    navigateToRecordInsulin = { navHostController.navigateToRecordInsulin() },
                )
            }

            bottomSheet(
                route = "${BottomSheetRoute.EditInsulinRecord.route}/{recordId}",
                arguments = listOf(navArgument("recordId") { type = NavType.StringType })
            ) {
                val recordIdArgument = it.arguments?.getString("recordId") ?: ""
                EditInsulinRecordBottomSheet(
                    recordId = recordIdArgument,
                    navigateToEditInsulinRecord = { navHostController.navigateToRecordInsulin(recordIdArgument) },
                    dismiss = { navHostController.navigateBack() }
                )
            }

            bottomSheet(
                route = "${BottomSheetRoute.EditInsulinReminder.route}/{reminderId}",
                arguments = listOf(navArgument("reminderId") { type = NavType.StringType })
            ) {
                val reminderIdArgument = it.arguments?.getString("reminderId") ?: ""
                EditRecordInsulinReminderBottomSheet(
                    reminderId = reminderIdArgument,
                    navigateToEditInsulinReminder = { navHostController.navigateToInsulinReminder() },
                    dismiss = { navHostController.navigateBack() }
                )
            }

            bottomSheet(
                route = "${BottomSheetRoute.EditGlucoseRecord.route}/{recordId}",
                arguments = listOf(navArgument("recordId") { type = NavType.StringType })
            ) {
                val recordIdArgument = it.arguments?.getString("recordId") ?: ""
                EditGlucoseRecordBottomSheet(
                    recordId = recordIdArgument,
                    navigateToEditGlucoseRecord = { navHostController.navigateToRecordGlucose(recordIdArgument) },
                    dismiss = { navHostController.navigateBack() }
                )
            }

            bottomSheet(
                route = "${BottomSheetRoute.EditGlucoseReminder.route}/{reminderId}",
                arguments = listOf(navArgument("reminderId") { type = NavType.StringType })
            ) {
                val reminderIdArgument = it.arguments?.getString("reminderId") ?: ""
                EditGlucoseReminderBottomSheet(
                    reminderId = reminderIdArgument,
                    navigateToGlucoseReminder = { navHostController.navigateToGlucoseReminder() },
                    dismiss = { navHostController.navigateBack() }
                )
            }
        }
    }
}