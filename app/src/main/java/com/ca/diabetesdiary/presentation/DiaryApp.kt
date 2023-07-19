package com.ca.diabetesdiary.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.diabetesdiary.navigation.MainNavHost
import com.ca.diabetesdiary.presentation.state.rememberDiaryAppState

@Composable
fun DiaryApp(
    viewModel: MainActivityViewModel
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val appState = rememberDiaryAppState()

    Scaffold(
        scaffoldState = appState.scaffoldState,
        snackbarHost = { appState.scaffoldState.snackbarHostState }
    ) { contentPadding ->
        MainNavHost(
            navHostController = appState.navController,
            startDestination = viewState.startDestination,
            shouldShowOnBoarding = viewState.shouldShowOnBoarding,
            modifier = Modifier.padding(contentPadding)
        )
    }

}