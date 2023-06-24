package com.ca.diabetesdiary.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ca.designsystem.theme.DiaryTheme
import com.ca.designsystem.theme.Theme
import com.ca.diabetesdiary.navigation.MainNavHost
import com.ca.diabetesdiary.navigation.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiaryTheme {
                MainScreen(viewModel = hiltViewModel())
            }
        }
    }
}

