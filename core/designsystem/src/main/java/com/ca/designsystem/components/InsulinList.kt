package com.ca.designsystem.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.domain.model.Insulin
import de.charlex.compose.*
import kotlin.math.absoluteValue

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

                val buttonSize by animateDpAsState(
                    targetValue = (revealState.offset.value.absoluteValue / 4).dp
                )

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
                                .padding(8.dp)
                                .background(Color.Gray.copy(alpha = 0.5f), CircleShape)
                                .size(buttonSize),
                            onClick = { editInsulin(insulin) }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(8.dp),
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    },
                    hiddenContentEnd = {
                        IconButton(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(Color.Gray.copy(alpha = 0.5f), CircleShape)
                                .size(buttonSize),
                            onClick = { deleteInsulin(insulin) }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(8.dp),
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    },
                    backgroundCardStartColor = Theme.colors.background,
                    backgroundCardEndColor = Theme.colors.background,
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