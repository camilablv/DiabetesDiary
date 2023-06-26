package com.ca.diabetesdiary.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.ca.designsystem.theme.Theme
import com.ca.diabetesdiary.navigation.MainNavHost

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Theme.colors.background
    ) {

        val navHostController = rememberNavController()
        MainNavHost(
            navHostController = navHostController,
            startDestination = viewState.startDestination
        )
    }
}