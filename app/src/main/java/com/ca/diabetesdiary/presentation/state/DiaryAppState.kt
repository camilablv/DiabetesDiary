package com.ca.diabetesdiary.presentation.state

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun rememberDiaryAppState(
    bottomSheetNavigator: BottomSheetNavigator = rememberBottomSheetNavigator(),
    navController: NavHostController = rememberNavController(bottomSheetNavigator),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) = remember(bottomSheetNavigator, navController, coroutineScope, scaffoldState) {
    DiaryAppState(
        bottomSheetNavigator,
        navController,
        coroutineScope,
        scaffoldState
    )
}

@Stable
class DiaryAppState @OptIn(ExperimentalMaterialNavigationApi::class) constructor(
    val bottomSheetNavigator: BottomSheetNavigator,
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val scaffoldState: ScaffoldState
)