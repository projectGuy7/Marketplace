package com.example.marketplace.presentation.viewmodels.loginmvi

import com.example.marketplace.presentation.viewmodels.basemvipattern.ViewState

data class LoginState(
    val loginField: String = "",
    val passwordField: String = "",
    val emailField: String = "",
    val verificationCode: String = "",
    val loading: Boolean = false,
    val error: String? = null
): ViewState