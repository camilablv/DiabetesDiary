package com.ca.designsystem.components.glucosemeasuringmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.model.MeasuringMark

@Composable
fun MeasuringMarkCard(mark: MeasuringMark) {
    val markIcon = measuringMarkIcons.find { it.mark == mark }!!
    Box(
        modifier = Modifier
            .size(48.dp)
            .padding(start = 8.dp)
            .background(markIcon.backgroundColor, Theme.shapes.large)
            .aspectRatio(1f)
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp),
            painter = painterResource(id = markIcon.icon),
            contentDescription = null
        )
    }
}