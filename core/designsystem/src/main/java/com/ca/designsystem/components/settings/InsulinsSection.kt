package com.ca.designsystem.components.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.InsulinList
import com.ca.designsystem.theme.Theme
import com.ca.domain.model.Insulin

@Composable
fun InsulinsSection(
    modifier: Modifier,
    insulins: List<Insulin>,
    revealedInsulin: Insulin?,
    onReveal: (Insulin) -> Unit,
    addInsulin: () -> Unit,
    deleteInsulin: (Insulin) -> Unit,
    editInsulin: (Insulin) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Insulin",
                style = Theme.typography.headlineSmall,
                modifier = Modifier,
                color = Theme.colors.onSurface
            )

            Button(
                onClick = { addInsulin() },
                shape = Theme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Theme.colors.secondary
                )
            ) {
                Text(
                    text = "Add",
                    color = Theme.colors.onSecondary
                )
            }
        }
        InsulinList(
            modifier = Modifier,
            insulins = insulins,
            revealedInsulin = revealedInsulin,
            onReveal = onReveal,
            deleteInsulin = deleteInsulin,
            editInsulin = editInsulin
        )
    }
}