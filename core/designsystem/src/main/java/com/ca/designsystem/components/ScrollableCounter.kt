package com.ca.designsystem.components

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ScrollableCounter(
    count: Int
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier
            .height(48.dp)
            .width(64.dp)
            .border(1.dp, Color.Black, Theme.shapes.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(100) {
            Text(
                text = (it + 1).toString(),
                modifier = Modifier
                    .size(48.dp),
                style = Theme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}