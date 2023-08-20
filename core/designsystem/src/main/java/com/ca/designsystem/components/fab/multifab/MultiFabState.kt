package com.ca.designsystem.components.fab.multifab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

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