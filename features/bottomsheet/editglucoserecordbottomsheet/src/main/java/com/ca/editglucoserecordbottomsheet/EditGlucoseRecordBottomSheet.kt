package com.ca.editglucoserecordbottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.components.BottomSheetContainer
import com.ca.designsystem.components.BottomSheetMenuOption

@Composable
fun EditGlucoseRecordBottomSheet(
    recordId: String,
    navigateToEditGlucoseRecord: () -> Unit,
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
            BottomSheetMenuOption(icon = R.drawable.round_edit, title = "Edit") {
                navigateToEditGlucoseRecord()
            }

            BottomSheetMenuOption(icon = R.drawable.round_delete, title = "Delete") {
                dismiss()
            }
        }
    }
}