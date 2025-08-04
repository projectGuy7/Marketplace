package com.example.marketplace.presentation.viewmodels.loginmvi

import com.example.marketplace.presentation.viewmodels.basemvipattern.ViewState
import com.example.marketplace.domain.marketplace.Token

data class LoginState(
    val loginField: String = "",
    val passwordField: String = "",
    val emailField: String = "",
    val verificationCode: String = "",
    val token: Token? = null,
    val loading: Boolean = false
): ViewState

