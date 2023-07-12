package com.ca.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.theme.Theme

@Composable
fun AddRecordActionButton(
    navigateToRecordGlucoseScreen: () -> Unit,
    navigateToRecordInsulinScreen: () -> Unit
) {
    val visible = remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AnimatedVisibility(visible = visible.value) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RecordMenuButton(
                    onClick = {
                        navigateToRecordGlucoseScreen()
                        visible.value = !visible.value
                },
                    icon = painterResource(id = R.drawable.glucose)
                )

                RecordMenuButton(
                    onClick = {
                        navigateToRecordInsulinScreen()
                        visible.value = !visible.value
                    },
                    icon = painterResource(id = R.drawable.vaccines)
                )
            }
        }

        FloatingActionButton(
            onClick = {
                visible.value = !visible.value
            },
            backgroundColor = Theme.colors.secondary,
            contentColor = Theme.colors.onSecondary
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "")
        }
    }
}

@Composable
private fun RecordMenuButton(
    onClick: () -> Unit,
    icon: Painter
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(56.dp),
        shape = CircleShape,
        elevation = ButtonDefaults.elevation(
            defaultElevation = Theme.elevations.default
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Theme.colors.background
        )
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            modifier = Modifier
                .size(48.dp)
        )
    }
}

@Composable
fun AddRecordMenu(
    visible: Boolean,
    navigateToRecordGlucoseScreen: () -> Unit,
    navigateToRecordInsulinScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        AnimatedVisibility(visible = true) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            ) {
                RecordMenuButton(
                    onClick = {
                        navigateToRecordGlucoseScreen()
                    },
                    icon = painterResource(id = R.drawable.glucose)
                )

                RecordMenuButton(
                    onClick = {
                        navigateToRecordInsulinScreen()
                    },
                    icon = painterResource(id = R.drawable.vaccines)
                )
            }
        }
    }
}