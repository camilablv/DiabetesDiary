package com.ca.setlocalebottomsheet.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.ca.setlocalebottomsheet.SetLocaleBottomSheet
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val setLocaleBottomSheetRoute = "set_locale_bottom_sheet_route"

fun NavController.navigateToSetLocaleBottomSheet() {
    navigate(setLocaleBottomSheetRoute)
}

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.setLocaleReminderBottomSheet(
    dismiss: () -> Unit
) {
    bottomSheet(
        route = setLocaleBottomSheetRoute
    ) {
        SetLocaleBottomSheet(dismiss)
    }
}