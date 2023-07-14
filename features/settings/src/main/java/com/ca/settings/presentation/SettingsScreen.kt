package com.ca.settings.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.*
import com.ca.designsystem.theme.Theme
import com.ca.model.Insulin
import com.ca.settings.presentation.components.SettingsSectionCard

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val viewState: SettingsViewState by viewModel.viewState.collectAsStateWithLifecycle()
    val scaffoldState = rememberScaffoldState()

    AddInsulinDialog(
        show = viewState.showAddInsulinDialog,
        add = { name, color, dose -> viewModel.addInsulin(name, color, dose) },
        onDismiss = { viewModel.setShowAddInsulinDialog(false) }
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ) {

        item {
            SettingsSectionCard(
                modifier = Modifier,
                sectionTitle = "Choose glucose unit"
            ) {
                GlucoseUnitsRadioButtons(
                    modifier = Modifier,
                    defaultUnit = viewState.glucoseUnits,
                    onSelect = { viewModel.setGlucoseUnit(it) }
                )
            }
        }

        item {
            InsulinSection(
                modifier = Modifier,
                insulins = viewState.insulins,
                addInsulin = {
                    viewModel.setShowAddInsulinDialog(true)
                },
                deleteInsulin = { viewModel.deleteInsulin(it) },
                editInsulin = {}
            )
        }

    }
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
                    val dismissState = rememberDismissState()

                    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                        deleteInsulin(insulin.id)
                    }

                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier,
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(0.3f) },
                        background = {}
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