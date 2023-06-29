package com.ca.diabetesdiary.presentation.state

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberDiaryAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) = remember(navController, coroutineScope, scaffoldState) {
    DiaryAppState(
        navController,
        coroutineScope,
        scaffoldState
    )
}

@Stable
class DiaryAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val scaffoldState: ScaffoldState
)