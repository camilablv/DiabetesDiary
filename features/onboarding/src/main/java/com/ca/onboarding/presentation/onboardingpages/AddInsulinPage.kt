package com.ca.onboarding.presentation.onboardingpages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.InsulinCard
import com.ca.designsystem.theme.Theme
import com.ca.model.Insulin
import com.ca.onboarding.presentation.components.AddInsulinButton

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddInsulinPage(
    insulins: List<Insulin>,
    addInsulin: (name: String, color: String, dose: Int) -> Unit,
    deleteInsulin: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Add Insulin",
            style = Theme.typography.headlineLarge,
            modifier = Modifier
                .weight(1f)
        )

        LazyColumn(
            modifier = Modifier
                .weight(7f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(
                count = insulins.size,
                key = { index -> insulins[index].id },
                itemContent = { index ->
                    val dismissState = rememberDismissState()

                    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                        deleteInsulin(insulins[index].id)
                    }

                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier,
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(0.3f)},
                        background = {}
                    ) {
                        InsulinCard(
                            modifier = Modifier,
                            insulin = insulins[index]
                        )
                    }


                }
            )
        }
        AddInsulinButton(add = { name, color, dose ->  addInsulin(name, color, dose) })
    }
}