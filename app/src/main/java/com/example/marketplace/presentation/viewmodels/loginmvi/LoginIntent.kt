package com.example.marketplace.presentation.viewmodels.loginmvi

import com.example.marketplace.presentation.viewmodels.basemvipattern.ViewIntent

sealed class LoginIntent: ViewIntent {
    data class UpdateLoginField(val login: String): LoginIntent()
    data class UpdatePasswordField(val password: String): LoginIntent()
    data class UpdateEmailField(val email: String): LoginIntent()
    data class UpdateCodeVerificationField(val codeVerification: String): LoginIntent()
    data object SendCredentials: LoginIntent()
    data object SendVerificationCode: LoginIntent()
}