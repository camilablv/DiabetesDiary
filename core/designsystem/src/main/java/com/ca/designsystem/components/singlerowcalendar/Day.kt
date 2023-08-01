package com.ca.designsystem.components.singlerowcalendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ca.common.utils.getDay3LettersName
import com.ca.designsystem.theme.Theme
import java.time.LocalDate

@Composable
internal fun Day(
    date: LocalDate,
    onClick: (LocalDate) -> Unit,
    modifier: Modifier,
    isSelected: Boolean
) {
    val textColor = if (isSelected) Theme.colors.onSecondary else Theme.colors.onBackground
    
    Column(
        modifier = modifier
            .clickable(
                enabled = date <= LocalDate.now()
            ) { onClick(date) }
            .width(36.dp)
            .height(36.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = date.getDay3LettersName(),
            style = Theme.typography.bodyMedium,
            color = textColor,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(6.dp)
                .aspectRatio(1f),
            text = date.dayOfMonth.toString(),
            style = Theme.typography.bodyLarge,
            color = if (date > LocalDate.now()) Color.Gray.copy(alpha = 0.5f) else textColor,
            textAlign = TextAlign.Center
        )
    }
}