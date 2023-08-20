package com.ca.designsystem.components.fab.multifab

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme


@Composable
fun MultiFloatingActionButton(
    modifier: Modifier,
    state: MutableState<MultiFabState> = rememberMultiFabState(),
    onMenuItemClicked: (MultiFabItem) -> Unit
) {
    val rotation by animateFloatAsState(
        if (state.value == MultiFabState.Expanded) 45f else 0f
    )

    Column(
        modifier = Modifier
            .onFocusChanged { if (!it.isFocused) state.value = MultiFabState.Collapsed },
        horizontalAlignment = Alignment.End
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
    items: List<MultiFabItem>,
    state: MutableState<MultiFabState>,
    onMenuItemClicked: (MultiFabItem) -> Unit
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
                items = items
            ) {
                FabMenuItem(
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
private fun FabMenuItem(
    item: MultiFabItem,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item.label?.let {
            Text(
                text = it,
                style = Theme.typography.bodyLarge
            )
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