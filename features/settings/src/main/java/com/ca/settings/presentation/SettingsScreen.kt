package com.ca.settings.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.authentication.GoogleAuthResultContract
import com.ca.designsystem.theme.Theme

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val viewState: SettingsViewState by viewModel.viewState.collectAsStateWithLifecycle()

    val googleAuthLauncher =
        rememberLauncherForActivityResult(GoogleAuthResultContract()) { result ->
            result
                .onSuccess { userData ->
                    viewModel.linkGoogleAccount(userData.idToken)
                }
                .onFailure {
                    //todo show error message
                }
        }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Settings Screen",
                style = Theme.typography.headlineMedium,
                modifier = Modifier,
                color = Color.Black
            )
            if (viewState.isAnonymousSignInMethod) {
                Button(
                    onClick = { googleAuthLauncher.launch(Unit) }
                ) {
                    Text("Link With Google Account")
                }
            } else {
                Button(
                    onClick = {  }
                ) {
                    Text("Sign Out")
                }
            }

        }
    }
}