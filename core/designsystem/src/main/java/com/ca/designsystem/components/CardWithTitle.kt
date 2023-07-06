package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@Composable
internal fun CardWithTitle(
    modifier: Modifier,
    title: String,
    icon: Painter? = null,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        elevation = Theme.elevations.default,
        shape = Theme.shapes.large
    ) {
        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            Theme.colors.secondary,
                            RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                        )
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = Theme.typography.bodyLarge,
                        color = Color.White
                    )
                    icon?.let {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            painter = it,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }

                content()
            }
        }
    }
}