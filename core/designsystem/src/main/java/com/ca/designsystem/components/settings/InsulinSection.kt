package com.ca.designsystem.components.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.InsulinCard
import com.ca.designsystem.theme.Theme
import com.ca.model.Insulin
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InsulinSection(
    modifier: Modifier,
    insulins: List<Insulin>,
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
                    RevealSwipe(
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
                                onClick = { /*TODO*/ }
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
                        backgroundCardEndColor = Theme.colors.background
                    ) {
                        InsulinCard(
                            modifier = Modifier
                                .clickable { editInsulin(insulin) },
                            insulin = insulin
                        )
                    }

                }
            }
        }
    }
}