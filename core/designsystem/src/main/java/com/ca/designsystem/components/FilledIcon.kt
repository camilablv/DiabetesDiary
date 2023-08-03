package com.ca.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@Composable
fun FilledIcon(backgroundColor: Color, @DrawableRes icon: Int) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(backgroundColor, Theme.shapes.large)
            .aspectRatio(1f)
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White
        )
    }
}