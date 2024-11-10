package com.ca.authentication.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ca.authentication.GoogleAuthResultContract
import com.ca.designsystem.R
import com.ca.designsystem.theme.Theme

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onComplete: () -> Unit
) {

    val googleAuthLauncher =
        rememberLauncherForActivityResult(GoogleAuthResultContract()) { result ->
            result
                .onSuccess { token ->
                    viewModel.signInWithGoogle(token)
                    onComplete()
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
                text = "Sign In With Google Screen",
                style = Theme.typography.headlineMedium,
                modifier = Modifier,
                color = Color.Black
            )
            Button(
                onClick = { googleAuthLauncher.launch(Unit) }
            ) {
                Text(stringResource(id = R.string.sign_in_with_google))
            }
        }
    }
}