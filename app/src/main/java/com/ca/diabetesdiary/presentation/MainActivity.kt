package com.ca.diabetesdiary.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ca.designsystem.theme.DiaryTheme
import com.ca.designsystem.theme.Theme
import com.ca.diabetesdiary.navigation.Route
import com.ca.getstarted.navigation.GetStartedNavHost
import com.ca.home.navigation.MainNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiaryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Theme.colors.background
                ) {
                    val startDestination =
                        if (viewModel.isUserSignedIn()) Route.Home.route else Route.GetStarted.route
                    val navHostController = rememberNavController()
                    NavHost(
                        navController = navHostController,
                        startDestination = startDestination
                    ) {
                        composable(Route.Home.route) {
                            MainNavHost(navController = rememberNavController())
                        }
                        composable(Route.GetStarted.route) {
                            GetStartedNavHost(
                                navController = rememberNavController(),
                                onComplete = {
                                    navHostController.navigate(Route.Home.route) {
                                        popUpTo(Route.GetStarted.route) { inclusive = true }
                                    }
                                },
                                signInAnonymously = { viewModel.signInAnonymously() }
                            )
                        }
                    }
                }
            }
        }
    }
}

