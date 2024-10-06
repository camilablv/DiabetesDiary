package com.ca.onboarding.presentation.onboardingpages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.components.InsulinList
import com.ca.designsystem.theme.Theme
import com.ca.model.Insulin

@Composable
fun AddInsulinPage(
    insulins: List<Insulin>,
    revealedInsulin: Insulin?,
    setRevealedInsulin: (Insulin) -> Unit,
    addInsulin: () -> Unit,
    deleteInsulin: (Insulin) -> Unit,
    editInsulin: (Insulin) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.add_insulin),
                style = Theme.typography.headlineLarge,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )

            IconButton(onClick = addInsulin) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }


        InsulinList(
            modifier = Modifier
                .weight(7f)
                .padding(horizontal = 16.dp),
            insulins = insulins,
            revealedInsulin = revealedInsulin,
            onReveal = { setRevealedInsulin(it) },
            deleteInsulin = { deleteInsulin(it) },
            editInsulin = { editInsulin(it) }
        )
    }
}