package com.ca.designsystem.components.glucosemeasuringmark

import androidx.compose.runtime.Composable
import com.ca.designsystem.components.FilledIcon
import com.ca.domain.model.MeasuringMark

@Composable
fun MeasuringMarkCard(mark: MeasuringMark) {
    val markIcon = measuringMarkIcons.find { it.mark == mark }!!
    FilledIcon(backgroundColor = markIcon.backgroundColor, icon = markIcon.icon)
}