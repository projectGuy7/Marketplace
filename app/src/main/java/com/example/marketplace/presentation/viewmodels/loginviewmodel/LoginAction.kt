package com.example.marketplace.presentation.viewmodels.loginviewmodel

import com.example.marketplace.presentation.viewmodels.basemvipattern.ViewAction

sealed class LoginAction: ViewAction {
    data class TypeInLoginField(val login: String): LoginAction()
    data class TypeInPasswordField(val password: String): LoginAction()
    data object SendCredentials: LoginAction()
}