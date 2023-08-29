package com.ca.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.domain.model.Page

@Composable
fun Tabs(
    pages: List<Page>,
    modifier: Modifier,
    selectedTabIndex: Int = 0,
    onTabClick: (Int) -> Unit,
    tabWidth: Dp = 150.dp
) {
    val indicatorOffset by animateDpAsState(
        targetValue = tabWidth * selectedTabIndex,
        animationSpec = tween(durationMillis = 150, easing = LinearEasing)
    )

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Theme.colors.onSecondary)
            .height(IntrinsicSize.Min)

    ) {
        TabIndicator(
            indicatorWidth = tabWidth,
            indicatorOffset = indicatorOffset,
            indicatorColor = Theme.colors.secondary
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(CircleShape)
        ) {
            pages.forEachIndexed { index, page ->
                TabItem(
                    selected = index == selectedTabIndex,
                    onClick = { onTabClick(index) },
                    text = page.text,
                    tabWidth
                )
            }
        }
    }
}

@Composable
private fun TabIndicator(
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(indicatorWidth)
            .offset(indicatorOffset)
            .clip(CircleShape)
            .background(indicatorColor)
    )
}

@Composable
private fun TabItem(
    selected: Boolean,
    onClick: () -> Unit,
    text: String,
    tabWidth: Dp
) {
    val textColor by animateColorAsState(
        targetValue = if (selected) Theme.colors.onSecondary else Theme.colors.onBackground,
        animationSpec = tween(easing = LinearEasing)
    )

    Text(
        text = text,
        modifier = Modifier
            .clip(CircleShape)
            .clickable(indication = null, interactionSource = MutableInteractionSource()) { onClick() }
            .width(tabWidth)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        color = textColor,
        textAlign = TextAlign.Center,
        style = Theme.typography.bodyLarge
    )
}