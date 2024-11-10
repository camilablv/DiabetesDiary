package com.ca.diabetesdiary.presentation.state

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
) = remember(navController, coroutineScope) {
    DiaryAppState(
        navController,
        coroutineScope
    )
}

@Stable
class DiaryAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
)