package com.ca.onboarding.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.data.model.Insulin
import com.ca.designsystem.components.InsulinEntry
import com.ca.designsystem.theme.Theme
import com.ca.onboarding.presentation.components.AddInsulinButton

@Composable
fun AddInsulinPage(
    insulins: List<Insulin>,
    addInsulin: (Insulin) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Add Insulin Type",
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
                    name = insulins[it].name,
                    color = insulins[it].color,
                    defaultDosage = insulins[it].defaultDosage.toString()
                )
            }
        }
        AddInsulinButton(add = { addInsulin(it) })
    }
}