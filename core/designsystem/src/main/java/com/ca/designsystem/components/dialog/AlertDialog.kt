package com.ca.designsystem.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.R

@Composable
fun DiaryAlertDialog(
    show: Boolean,
    title: String,
    text: String,
    onDismiss: () -> Unit,
    onPositiveButtonClick: () -> Unit,
    positiveButtonText: String
) {
    if (!show) return
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = { Text(text = text) },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismiss) {
                    Text(
                        text = stringResource(id = R.string.dismiss),
                        color = Theme.colors.secondary
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    onClick = onPositiveButtonClick,
                    shape = Theme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Theme.colors.secondary
                    )
                ) {
                    Text(
                        text = positiveButtonText,
                        color = Theme.colors.onSecondary
                    )
                }
            }
        },
        shape = Theme.shapes.large
    )
}