package com.ca.diabetesdiary.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.theme.DiaryTheme
import com.ca.diabetesdiary.presentation.state.rememberDiaryAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val viewState = viewModel.viewState.collectAsStateWithLifecycle()
            val startDestination = viewModel.startDestination()
            val appState = rememberDiaryAppState()

            DiaryTheme(viewState.value.darkMode) {
                DiaryApp(
                    appState = appState,
                    startDestination = startDestination
                )
            }
        }
    }
}

val LocalMutableContext = staticCompositionLocalOf<MutableState<Context>> {
    error("LocalMutableContext not provided")
}

