package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@Composable
fun RecordsMenuBottomSheet() {
    Box(
        modifier = Modifier
            .background(Theme.colors.secondary, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .fillMaxWidth()
            .height(150.dp)
            ,
        contentAlignment = Alignment.Center
    ) {
        Text("This is a cool bottom sheet!")
    }
}