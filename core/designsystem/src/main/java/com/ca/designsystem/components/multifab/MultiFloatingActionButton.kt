package com.ca.designsystem.components.multifab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

sealed class MultiFabState {
    object Collapsed : MultiFabState()
    object Expanded : MultiFabState()

    fun isExpanded() = this == Expanded

    fun toggleValue() = if (isExpanded()) Collapsed else Expanded
}

@Composable
fun rememberMultiFabState() = remember {
    mutableStateOf<MultiFabState>(MultiFabState.Collapsed)
}

@Composable
fun MultiFloatingActionButton(
    modifier: Modifier,
    state: MutableState<MultiFabState> = rememberMultiFabState(),
    onMenuItemClicked: (RecordMenuItem) -> Unit
) {
    val rotation by animateFloatAsState(
        if (state.value == MultiFabState.Expanded) 45f else 0f
    )

    Column(
        modifier = Modifier

    ) {
        FabMenu(
            modifier = Modifier,
            items = multiFabItems,
            state = state,
            onMenuItemClicked = {
                onMenuItemClicked(it)
            }
        )

        FloatingActionButton(
            onClick = {
                state.value = state.value.toggleValue()
            },
            backgroundColor = Theme.colors.secondary,
            contentColor = Theme.colors.onSecondary,
            modifier = Modifier
        ) {
            Icon(
                modifier = Modifier
                    .rotate(rotation),
                imageVector = Icons.Filled.Add,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun FabMenu(
    modifier: Modifier,
    items: List<RecordMenuItem>,
    state: MutableState<MultiFabState>,
    onMenuItemClicked: (RecordMenuItem) -> Unit
) {
    AnimatedVisibility(
        visible = state.value.isExpanded(),
        enter = fadeIn() + expandVertically(),
        exit = fadeOut()
    ) {
        LazyColumn(
            modifier = modifier
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End,
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(
                items = items,
                key = { it.id }
            ) {
                FabMenuButton(
                    item = it,
                    onClick = {
                        onMenuItemClicked(it)
                        state.value = MultiFabState.Collapsed
                    }
                )
            }
        }
    }
}

@Composable
private fun FabMenuButton(
    item: RecordMenuItem,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item.label?.let {
            Text(text = it)
        }

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
                painter = painterResource(id = item.icon),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
            )
        }
    }

}