package com.ca.settings.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.*
import com.ca.designsystem.components.settings.GlucoseUnitsSection
import com.ca.designsystem.components.settings.LanguageSection
import com.ca.designsystem.components.settings.ThemeSection
import com.ca.designsystem.components.topbar.MainTopBar
import com.ca.designsystem.theme.Theme
import com.ca.model.Insulin

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun InsulinSection(
    modifier: Modifier,
    insulins: List<Insulin>,
    addInsulin: () -> Unit,
    deleteInsulin: (String) -> Unit,
    editInsulin: (String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Insulin",
                style = Theme.typography.headlineSmall,
                modifier = Modifier,
                color = Theme.colors.onSurface
            )

            Button(onClick = { addInsulin() }) {
                Text(text = "Add")
            }
        }
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            insulins.forEach { insulin ->
                key(insulin.id) {
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            true

                        }
                    )

                    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                        deleteInsulin(insulin.id)
                    }

                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier,
                        directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
                        dismissThresholds = { FractionalThreshold(0.3f) },
                        background = {
                            DismissBackground(dismissState = dismissState)
                        }
                    ) {
                        InsulinCard(
                            modifier = Modifier
                                .clickable { editInsulin(insulin.id) },
                            insulin = insulin
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DismissBackground(
    dismissState: DismissState
) {
    val color by animateColorAsState(
        targetValue = when(dismissState.targetValue) {
            DismissValue.Default -> Color.LightGray
            DismissValue.DismissedToEnd -> Color.Blue
            DismissValue.DismissedToStart -> Color.Red
        }
    )

    val size by animateDpAsState(
        targetValue = if(dismissState.isAnimationRunning) 12.dp else 36.dp
    )

    Row(
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth()
            .background(color, Theme.shapes.large)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier
                .size(36.dp)
                .background(Color.Gray.copy(alpha = 0.5f), CircleShape),
            onClick = { /*TODO*/ }
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "",
                tint = Color.White
            )
        }
        IconButton(
            modifier = Modifier
                .size(36.dp)
                .background(Color.Gray.copy(alpha = 0.5f), CircleShape),
            onClick = { /*TODO*/ }
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}


