package com.ca.onboarding.presentation.onboardingpages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.model.Insulin
import com.ca.designsystem.components.InsulinCard
import com.ca.designsystem.theme.Theme
import com.ca.onboarding.presentation.components.AddInsulinButton

@Composable
fun AddInsulinPage(
    insulins: List<Insulin>,
    addInsulin: (Insulin) -> Unit
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
            items(insulins.size) {
                InsulinCard(
                    insulin = insulins[it]
                )
            }
        }
        AddInsulinButton(add = { addInsulin(it) })
    }
}