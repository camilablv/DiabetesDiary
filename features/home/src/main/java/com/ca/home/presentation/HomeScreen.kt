package com.ca.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ca.designsystem.components.multifab.MultiFloatingActionButton
import com.ca.designsystem.components.multifab.MultiFabItem
import com.ca.designsystem.theme.Theme

@Composable
fun HomeScreen(
    navigateToRecordGlucoseScreen: () -> Unit,
    navigateToRecordInsulinScreen: () -> Unit
) {

    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                modifier = Modifier,
                onMenuItemClicked = {
                    when(it) {
                        MultiFabItem.RecordInsulin -> { navigateToRecordInsulinScreen() }
                        MultiFabItem.RecordGlucose -> { navigateToRecordGlucoseScreen() }
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Home",
                style = Theme.typography.headlineMedium,
                modifier = Modifier,
                color = Color.Black
            )
        }
    }

}