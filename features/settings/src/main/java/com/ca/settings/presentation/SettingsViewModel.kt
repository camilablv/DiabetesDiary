package com.ca.settings.presentation

import androidx.lifecycle.ViewModel
import com.ca.settings.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {

    private var _viewState = MutableStateFlow(SettingsViewState())
    val viewState: StateFlow<SettingsViewState> = _viewState

    init {
        _viewState.update { it.copy(isAnonymousSignInMethod = repository.isAnonymousSignInMethod()) }
    }

    fun linkGoogleAccount(token: String) {
        repository.linkWithGoogleAccount(token) {
            _viewState.update { it.copy(isAnonymousSignInMethod = false) }
        }
    }

}