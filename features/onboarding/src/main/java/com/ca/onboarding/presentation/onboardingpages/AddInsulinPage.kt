package com.ca.onboarding.presentation.onboardingpages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.model.Insulin
import com.ca.designsystem.components.InsulinEntry
import com.ca.designsystem.theme.Theme
import com.ca.onboarding.presentation.components.AddInsulinButton

@Composable
fun AddInsulinPage(
    insulins: List<com.ca.model.Insulin>,
    addInsulin: (com.ca.model.Insulin) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            text = "Add Insulin",
            style = Theme.typography.headlineLarge,
            modifier = Modifier
                .weight(1f)
        )

        LazyColumn(
            modifier = Modifier
                .weight(5f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(insulins.size) {
                InsulinEntry(
                    insulin = insulins[it]
                )
            }
        }
        AddInsulinButton(add = { addInsulin(it) })
    }
}