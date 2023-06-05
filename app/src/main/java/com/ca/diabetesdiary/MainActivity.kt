package com.ca.diabetesdiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ca.designsystem.theme.DiaryTheme
import com.ca.designsystem.theme.Theme
import com.ca.getstarted.navigation.GetStartedNavHost
import com.ca.home.navigation.MainNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Theme.colors.background
                ) {
                    val isLoggedIn = false
                    val startDestination = if (isLoggedIn) "home" else "getStarted"
                    val navHostController = rememberNavController()
                    NavHost(navController = navHostController, startDestination = startDestination) {
                        composable("home") {
                            MainNavHost(navController = rememberNavController())
                        }
                        composable("getStarted") {
                            GetStartedNavHost(
                                navController = rememberNavController(),
                                onComplete = {
                                    navHostController.navigate("home") {
                                        popUpTo("home") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

