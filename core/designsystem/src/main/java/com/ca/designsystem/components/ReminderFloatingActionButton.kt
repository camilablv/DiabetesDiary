package com.ca.designsystem.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.theme.Theme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReminderFloatingActionButton(
    pagerState: PagerState,
    navigateToAddInsulinReminder: () -> Unit,
    navigateToAddGlucoseReminder: () -> Unit
) {
    val size by animateDpAsState(
        targetValue = if (pagerState.isScrollInProgress) 52.dp else 56.dp,
        animationSpec = tween(
            durationMillis = 150,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    FloatingActionButton(
        modifier = Modifier
            .size(size),
        backgroundColor = Theme.colors.secondary,
        onClick = {
            when(pagerState.currentPage) {
                0 -> { navigateToAddInsulinReminder() }
                1 -> { navigateToAddGlucoseReminder() }
            }
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.round_notification_add),
            contentDescription = null,
            tint = Theme.colors.onSecondary
        )
    }

}