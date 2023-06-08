package com.ca.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ca.designsystem.theme.Theme

@Composable
fun HomeScreen(
    navigateToRecordGlucoseScreen: () -> Unit,
    navigateToRecordInsulinScreen: () -> Unit
) {

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Home",
                style = Theme.typography.headlineMedium,
                modifier = Modifier,
                color = Color.Black
            )
            Button(onClick = { navigateToRecordGlucoseScreen() }) {
                Text("Record Glucose")
            }
            Button(onClick = { navigateToRecordInsulinScreen() }) {
                Text("Record Insulin")
            }
        }
    }
}