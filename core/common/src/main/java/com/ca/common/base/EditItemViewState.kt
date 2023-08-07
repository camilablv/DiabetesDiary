package com.ca.common.base

import com.ca.model.ListItem

data class EditItemViewState(
    val isInEditMode: Boolean = false,
    val selectedItem: ListItem? = null
)
