package com.ca.platform.viewmodel

import androidx.lifecycle.ViewModel
import com.ca.model.ListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class EditModeState(
    val isInEditMode: Boolean = false,
    val selectedItem: ListItem? = null
)

abstract class BaseViewModel : ViewModel() {

    private val _editModeState = MutableStateFlow(EditModeState())
    val editModeState: StateFlow<EditModeState>
        get() = _editModeState.asStateFlow()

    private fun setEditMode(boolean: Boolean) {
        _editModeState.update { it.copy(isInEditMode = boolean) }
    }

    private fun setSelectedItem(selectedItem: ListItem?) {
        _editModeState.update { it.copy(selectedItem = selectedItem) }
    }

    abstract fun removeItem(item: ListItem?)

    fun enableEditMode(selectedItem: ListItem?) {
        setEditMode(true)
        setSelectedItem(selectedItem)
    }

    fun disableEditMode() {
        setEditMode(false)
        setSelectedItem(null)
    }

    fun isItemSelected(item: ListItem) = editModeState.value.selectedItem == item
}