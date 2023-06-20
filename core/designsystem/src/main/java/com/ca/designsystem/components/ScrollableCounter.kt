package com.ca.designsystem.components

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ScrollableCounter(
) {
    val scrollState = rememberScrollState()
    var count by remember { mutableStateOf(0) }

    Row {
        CounterButton(text = "-") { count-- }

        Box(
            modifier = Modifier
                .border(1.dp, Color.Gray, Theme.shapes.medium)
        ) {
            Counter(
                count = count ,
                scrollState = scrollState
            )
        }

        CounterButton(text = "+") { count++ }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Counter(
    count: Int,
    scrollState: ScrollState
) {
    AnimatedContent(
        targetState = count,
        transitionSpec = {
            if (targetState > initialState) {
                slideInVertically { -it } with slideOutVertically { it }
            } else {
                slideInVertically { it } with slideOutVertically { -it }
            }
        }
    ) {
        Text(
            text = count.toString(),
            modifier = Modifier
                .height(48.dp)
                .width(64.dp)
                .verticalScroll(
                    state = scrollState,
                    enabled = true,
                    flingBehavior = ScrollableDefaults.flingBehavior(),
                    reverseScrolling = true
                ),
            style = Theme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CounterButton(
    text: String,
    onClick: () -> Unit
) {
    IconButton(onClick = { onClick() }) {
        Text(
            text = text,
            style = Theme.typography.headlineLarge
        )
    }
}