package com.ca.authentication.presentation

import androidx.lifecycle.ViewModel
import com.ca.authentication.domain.usecase.SignInWithGoogleUseCase
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
) : ViewModel() {


}