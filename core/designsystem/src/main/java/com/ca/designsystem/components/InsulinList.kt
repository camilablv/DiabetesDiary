package com.ca.designsystem.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.ca.model.Insulin
import de.charlex.compose.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InsulinList(
    modifier: Modifier,
    insulins: List<Insulin>,
    revealedInsulin: Insulin?,
    onReveal: (Insulin) -> Unit,
    deleteInsulin: (Insulin) -> Unit,
    editInsulin: (Insulin) -> Unit
) {
    Column(
        modifier = modifier,
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
                    modifier = Modifier,
                    directions = setOf(
                        RevealDirection.StartToEnd,
                        RevealDirection.EndToStart
                    ),
                    shape = RoundedCornerShape(12.dp),
                    hiddenContentStart = {
                            IconButton(
                                modifier = Modifier,
                                onClick = { editInsulin(insulin) }
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(28.dp),
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                    },
                    hiddenContentEnd = {
                        IconButton(
                            modifier = Modifier,
                            onClick = { deleteInsulin(insulin) }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(28.dp),
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    },
                    backgroundCardStartColor = Color.Gray.copy(alpha = 0.3f),
                    backgroundCardEndColor = Color.Gray.copy(alpha = 0.3f),
                ) {
                    InsulinCard(
                        modifier = Modifier,
                        insulin = insulin,
                        onClick = { editInsulin(insulin) }
                    )
                }

            }
        }
    }
}