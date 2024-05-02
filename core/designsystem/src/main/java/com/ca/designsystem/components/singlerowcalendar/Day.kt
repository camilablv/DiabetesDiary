package com.ca.designsystem.components.singlerowcalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ca.common.utils.dayName
import com.ca.designsystem.theme.Theme
import java.time.LocalDate
import java.util.Locale

@Composable
internal fun Day(
    date: LocalDate,
    onClick: (LocalDate) -> Unit,
    modifier: Modifier,
    isSelected: Boolean,
    locale: Locale
) {
    val textColor = if (isSelected) Theme.colors.onSecondary else Theme.colors.onBackground
    
    Column(
        modifier = modifier
            .clickable(
                enabled = date <= LocalDate.now(),
                indication = null,
                interactionSource = MutableInteractionSource(),
            ) { onClick(date) }
            .width(36.dp)
            .wrapContentHeight()
        ,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = date.dayName(locale),
            style = Theme.typography.bodyMedium,
            color = Theme.colors.onBackground,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .background(
                    color = if (isSelected) Theme.colors.secondary else Theme.colors.background,
                    shape = CircleShape
                )
                .padding(6.dp)
                .size(24.dp)
                .aspectRatio(1f),
            text = date.dayOfMonth.toString(),
            style = Theme.typography.bodyLarge,
            color = if (date > LocalDate.now()) Color.Gray.copy(alpha = 0.3f) else textColor,
            textAlign = TextAlign.Center
        )
    }
}