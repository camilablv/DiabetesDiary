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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.R
import com.ca.designsystem.components.dialog.DiaryAlertDialog
import com.ca.designsystem.components.dialog.EditInsulinDialog
import com.ca.designsystem.components.dialog.SetLocaleDialog
import com.ca.designsystem.components.settings.GlucoseUnitsSection
import com.ca.designsystem.components.settings.InsulinsSection
import com.ca.designsystem.components.settings.LanguageSection
import com.ca.designsystem.components.settings.ThemeSection
import com.ca.designsystem.components.topbar.TopBar
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    navigateBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val viewState: SettingsViewState by viewModel.viewState.collectAsStateWithLifecycle()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    val localeOptions = mapOf(
        "en" to R.string.en,
        "uk" to R.string.uk
    ).mapValues { stringResource(it.value) }

    fun currentLocale() = context.resources.configuration.locales[0]

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(title = stringResource(id = R.string.settings)) {
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
                    selectedLanguage = localeOptions.getValue(currentLocale().language),
                    onClick = { viewModel.showSetLocaleDialog(true) }
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
        title = stringResource(id = R.string.delete_insulin_title),
        text = stringResource(id = R.string.delete_insulin_text),
        onDismiss = { viewModel.showDeleteInsulinDialog(false) },
        onPositiveButtonClick = { viewModel.deleteInsulinWithReminders(viewState.revealedInsulin) },
        positiveButtonText = stringResource(id = R.string.delete)
    )

    EditInsulinDialog(
        show = viewState.showEditInsulinDialog,
        edit = { id, name, color, dose -> viewModel.editInsulin(id, name, color, dose) },
        onDismiss = { viewModel.showEditInsulinDialog(false) },
        editableInsulin = viewState.revealedInsulin
    )

    val scope = rememberCoroutineScope()

    SetLocaleDialog(
        show = viewState.showSetLocaleDialog,
        onDismiss = { viewModel.showSetLocaleDialog(false) },
        locales = mapOf("en" to "English", "uk" to "Українська"),
        selectedLocale = currentLocale(),
        selectLocale = {
            scope.launch {
                viewModel.showSetLocaleDialog(false)
                viewModel.selectLocale(it)
            }
        }
    )
}
