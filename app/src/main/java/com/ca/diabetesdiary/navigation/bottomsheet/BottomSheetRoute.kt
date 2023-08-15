package com.ca.diabetesdiary.navigation.bottomsheet

sealed class BottomSheetRoute(val route: String) {
    object RecordsMenu : BottomSheetRoute("records_menu")
    object EditInsulinRecord : BottomSheetRoute("edit_insulin_record_bottom_sheet")
    object EditGlucoseRecord : BottomSheetRoute("edit_glucose_record_bottom_sheet")
    object EditInsulinReminder : BottomSheetRoute("edit_insulin_reminder_bottom_sheet")
    object EditGlucoseReminder : BottomSheetRoute("edit_glucose_reminder_bottom_sheet")
}
