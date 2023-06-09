package com.ca.authentication.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ca.designsystem.theme.Theme

@Composable
fun AuthScreen() {

    val googleAuthLauncher = rememberLauncherForActivityResult(GoogleAuthResult()) {

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
            Button(onClick = { googleAuthLauncher.launch(Unit) }) {
                Text("Sign In With Google")
            }
        }
    }
}