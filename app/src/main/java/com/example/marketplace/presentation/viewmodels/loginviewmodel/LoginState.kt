package com.example.marketplace.presentation.viewmodels.loginviewmodel

import com.example.marketplace.presentation.viewmodels.basemvipattern.ViewState

sealed class LoginState: ViewState {
    data object Loading: LoginState()
    data class Fields(
        val loginField: String = "",
        val passwordField: String = ""
    ): LoginState()
}