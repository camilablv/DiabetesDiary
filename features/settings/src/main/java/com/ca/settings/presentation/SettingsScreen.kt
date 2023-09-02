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
import com.ca.designsystem.components.dialog.DiaryAlertDialog
import com.ca.designsystem.components.dialog.EditInsulinDialog
import com.ca.designsystem.components.settings.GlucoseUnitsSection
import com.ca.designsystem.components.settings.InsulinsSection
import com.ca.designsystem.components.settings.LanguageSection
import com.ca.designsystem.components.settings.ThemeSection
import com.ca.designsystem.components.topbar.TopBar

@Composable
fun SettingsScreen(
    navigateBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val viewState: SettingsViewState by viewModel.viewState.collectAsStateWithLifecycle()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(title = "Settings") {
            navigateBack()
        } }
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
                InsulinsSection(
                    modifier = Modifier,
                    insulins = viewState.insulins,
                    revealedInsulin = viewState.revealedInsulin,
                    onReveal = { viewModel.setRevealedInsulin(it) },
                    addInsulin = {
                        viewModel.setRevealedInsulin(null)
                        viewModel.showEditInsulinDialog(true)
                    },
                    deleteInsulin = {
                        viewModel.setRevealedInsulin(it)
                        viewModel.deleteInsulin(it)
                    },
                    editInsulin = {
                        viewModel.setRevealedInsulin(it)
                        viewModel.showEditInsulinDialog(true)
                    }
                )
            }
        }
    }

    DiaryAlertDialog(
        show = viewState.showDeleteInsulinDialog,
        onDismiss = { viewModel.showDeleteInsulinDialog(false) },
        onPositiveButtonClick = { viewModel.deleteInsulinWithReminders(viewState.revealedInsulin) },
        positiveButtonText = "Delete"
    )

    EditInsulinDialog(
        show = viewState.showEditInsulinDialog,
        edit = { id, name, color, dose -> viewModel.editInsulin(id, name, color, dose) },
        onDismiss = { viewModel.showEditInsulinDialog(false) },
        editableInsulin = viewState.revealedInsulin
    )
}
