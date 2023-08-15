package com.ca.editinsulinreminderbottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.BottomSheetContainer
import com.ca.designsystem.components.BottomSheetMenuOption

@Composable
fun EditRecordInsulinReminderBottomSheet(
    reminderId: Int,
    navigateToEditInsulinReminder: () -> Unit,
    dismiss: () -> Unit
) {
    BottomSheetContainer {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            BottomSheetMenuOption(icon = com.ca.designsystem.R.drawable.round_delete, title = "Delete") {
                dismiss()
            }
            BottomSheetMenuOption(icon = com.ca.designsystem.R.drawable.round_edit, title = "Edit") {
                navigateToEditInsulinReminder()
            }
            BottomSheetMenuOption(icon = com.ca.designsystem.R.drawable.round_notifications_off, title = "Switch off") {
                dismiss()
            }
        }
    }
}