package com.ca.designsystem.components.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.InsulinCard
import com.ca.designsystem.theme.Theme
import com.ca.model.Insulin
import de.charlex.compose.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InsulinSection(
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
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            insulins.forEach { insulin ->
                key(insulin.id) {
                    val revealState = rememberRevealState()

                    LaunchedEffect(revealState.progress) {
                        if (revealState.targetValue != RevealValue.Default)
                            onReveal(insulin)
                    }

                    LaunchedEffect(revealedInsulin) {
                        val isItemRevealed = insulin == revealedInsulin
                        if (!isItemRevealed) revealState.reset()
                    }

                    RevealSwipe(
                        state = revealState,
                        modifier = Modifier.padding(vertical = 5.dp),
                        directions = setOf(
                            RevealDirection.StartToEnd,
                            RevealDirection.EndToStart
                        ),
                        hiddenContentStart = {
                            IconButton(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(36.dp)
                                    .background(Color.Gray.copy(alpha = 0.5f), CircleShape),
                                onClick = { editInsulin(insulin) }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        },
                        hiddenContentEnd = {
                            IconButton(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(36.dp)
                                    .background(Color.Gray.copy(alpha = 0.5f), CircleShape),
                                onClick = { deleteInsulin(insulin) }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        },
                        backgroundCardStartColor = Theme.colors.background,
                        backgroundCardEndColor = Theme.colors.background,
                        closeOnContentClick = true,
                        closeOnBackgroundClick = true
                    ) {
                        InsulinCard(
                            modifier = Modifier,
                            insulin = insulin
                        )
                    }

                }
            }
        }
    }
}