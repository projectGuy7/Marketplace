package com.example.marketplace.presentation.viewmodels.loginviewmodel

import com.example.marketplace.presentation.viewmodels.basemvipattern.ViewIntent

sealed class LoginIntent: ViewIntent {
    data class TypeInLoginField(val login: String): LoginIntent()
    data class TypeInPasswordField(val password: String): LoginIntent()
    data object SendCredentials: LoginIntent()
}