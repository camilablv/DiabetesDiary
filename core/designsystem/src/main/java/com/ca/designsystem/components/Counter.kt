package com.ca.designsystem.components

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.DiaryTheme
import com.ca.designsystem.theme.Grey100
import com.ca.designsystem.theme.Theme


@SuppressLint("UnrememberedMutableState")
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
        CounterButton {
            previousValueState.value = value
            decrement()
        }

        Box(
            modifier = Modifier
                .background(Grey100, Theme.shapes.large)
                .width(75.dp),
            contentAlignment = Alignment.Center
        ) {
            CounterTextField(value = mutableStateOf(value.toString()), onValueChanged = onValueChanged)
        }

        CounterButton {
            previousValueState.value = value
            increment()
        }
    }
}

@Composable
private fun CounterButton(
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Rounded.AddCircle,
            contentDescription = "",
            modifier = Modifier.size(24.dp),
            tint = Theme.colors.primary
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CounterTextField(
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
private fun CounterPreview() {
    DiaryTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
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