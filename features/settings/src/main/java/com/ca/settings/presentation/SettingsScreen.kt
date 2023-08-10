package com.ca.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.AddInsulinDialog
import com.ca.designsystem.components.settings.GlucoseUnitsSection
import com.ca.designsystem.components.settings.InsulinSection
import com.ca.designsystem.components.settings.LanguageSection
import com.ca.designsystem.components.settings.ThemeSection
import com.ca.designsystem.components.topbar.MainTopBar

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val viewState: SettingsViewState by viewModel.viewState.collectAsStateWithLifecycle()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MainTopBar(title = "Diabetes Diary") }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                ThemeSection(
                    isDarkThemeMode = viewState.darkMode,
                    onCheckedChange = { viewModel.darkMode(it) }
                )
            }

            item {
                LanguageSection(
                    selectedLanguage = "English",
                    onClick = {}
                )
            }

            item {
                GlucoseUnitsSection(
                    selectedUnits = viewState.glucoseUnits,
                    onSelect = { viewModel.setGlucoseUnit(it) }
                )
            }

            item {
                InsulinSection(
                    modifier = Modifier,
                    insulins = viewState.insulins,
                    addInsulin = {
                        viewModel.showAddInsulinDialog(true)
                    },
                    deleteInsulin = { viewModel.deleteInsulin(it) },
                    editInsulin = { viewModel.showEditInsulinDialog(true) }
                )
            }

        }
    }

    AddInsulinDialog(
        show = viewState.showAddInsulinDialog,
        add = { name, color, dose -> viewModel.addInsulin(name, color, dose) },
        onDismiss = { viewModel.showAddInsulinDialog(false) }
    )
}
