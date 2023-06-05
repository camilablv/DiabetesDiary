package com.ca.diabetesdiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ca.designsystem.theme.DiaryTheme
import com.ca.getstarted.navigation.GetStartedNavHost
import com.ca.home.navigation.MainNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
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
                                    navHostController.navigate("home")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    startDestination: String
) {
    GetStartedNavHost(
        navController = navHostController,
        onComplete = {}
    )
}
