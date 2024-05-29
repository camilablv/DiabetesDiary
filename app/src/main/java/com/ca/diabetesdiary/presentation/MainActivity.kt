package com.ca.diabetesdiary.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            CompositionLocalProvider(
                LocalMutableContext provides remember {
                    mutableStateOf(context)
                }
            ) {
                CompositionLocalProvider(
                    LocalContext provides LocalMutableContext.current.value
                ) {
                    DiaryApp(viewModel = hiltViewModel())
                }
            }
        }
    }
}

val LocalMutableContext = staticCompositionLocalOf<MutableState<Context>> {
    error("LocalMutableContext not provided")
}

