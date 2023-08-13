package com.ca.designsystem.components

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.theme.DiaryTheme
import com.ca.designsystem.theme.Theme


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Counter(
    modifier: Modifier,
    value: Int,
    increment: () -> Unit,
    decrement: () -> Unit,
    onValueChanged: (String) -> Unit
) {
    val previousValueState = remember { mutableStateOf(value) }

    Row(
        modifier = modifier
            .wrapContentWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            onClick = {
                previousValueState.value = value
                decrement()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.remove),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Theme.colors.primary
            )
        }

        Box(
            modifier = Modifier
                .border(1.dp, Color.Gray, Theme.shapes.medium)
        ) {
            AnimatedContent(
                targetState = value,
                transitionSpec = {
                    if (value > previousValueState.value) {
                        slideInVertically { fullHeight -> -fullHeight } + fadeIn() with
                                slideOutVertically { fullHeight -> fullHeight } + fadeOut()
                    } else {
                        slideInVertically { fullHeight -> fullHeight } + fadeIn() with
                                slideOutVertically { fullHeight -> -fullHeight } + fadeOut()
                    }.using(SizeTransform(clip = true))
                }
            ) {
                CounterTextField(value = mutableStateOf(value.toString()), onValueChanged = onValueChanged)
            }

        }

        IconButton(
            onClick = {
                previousValueState.value = value
                increment()
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.AddCircle,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = Theme.colors.primary
            )
        }
    }
}

@Composable
fun <T>CarouselCounter(
    list: List<T>,
    modifier: Modifier,
    onSelected: () -> Unit
) {
    val state = rememberLazyListState()

    LazyColumn(
        state = state,
        modifier = modifier
            .height(48.dp)
            .width(64.dp)
    ) {
        items(list.size) {
            Text(
                text = list[it].toString(),
                style = Theme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CounterTextField(
    value: MutableState<String>,
    onValueChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = value.value,
        onValueChange = { string ->
            value.value = string
            onValueChanged(string)
        },
        textStyle = Theme.typography.counter,
        modifier = Modifier
            .width(64.dp)
            .height(48.dp)
            .onFocusChanged {
                if (!it.isCaptured) onValueChanged(value.value)
            },

        decorationBox = {
            it()
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onValueChanged(value.value)
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
        cursorBrush = Brush.verticalGradient(
            0.00f to Color.Transparent,
            0.27f to Color.Transparent,
            0.27f to Theme.colors.onSurface,
            0.80f to Theme.colors.onSurface,
            0.80f to Color.Transparent,
            1.00f to Color.Transparent
        )
    )
}

@Composable
@Preview
fun CounterPreview() {
    DiaryTheme() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Counter(
                modifier = Modifier
                    .align(Alignment.Center),
                value = 1,
                increment = {},
                decrement = {},
                onValueChanged = {}
            )
        }
    }
}