package com.example.marketplace.presentation.viewmodels.loginmvi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import com.example.marketplace.di.LoginViewModelFactory
import com.example.marketplace.domain.marketplace.Token
import com.example.marketplace.domain.marketplace.User
import com.example.marketplace.domain.repository.LoginRepository
import com.example.marketplace.domain.util.Resource
import com.example.marketplace.presentation.SnackbarController
import com.example.marketplace.presentation.SnackbarEvent
import com.example.marketplace.presentation.navigation.VerificationCodeScreen
import com.example.marketplace.presentation.viewmodels.basemvipattern.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream


@HiltViewModel(assistedFactory = LoginViewModelFactory::class)
class LoginViewModel @AssistedInject constructor(
    val repository: LoginRepository,
    @Assisted val backstack: NavBackStack,
    @Assisted val tokenFile: File
) : BaseViewModel<LoginIntent, LoginState>() {

    override var state by mutableStateOf(LoginState())

    override fun handleIntent(intent: LoginIntent) {
        viewModelScope.launch {
            when(intent) {
                is LoginIntent.UpdateLoginField -> {
                    state = state.copy(loginField = intent.login)
                }
                is LoginIntent.UpdatePasswordField -> {
                    state = state.copy(passwordField = intent.password)
                }
                is LoginIntent.UpdateCodeVerificationField -> {
                    state = state.copy(verificationCode = intent.codeVerification)
                }
                is LoginIntent.UpdateEmailField -> {
                    state = state.copy(emailField = intent.email)
                }
                LoginIntent.SendCredentials -> {
                    state = state.copy(loading = true)
                    when(val result = repository.register(
                        User(
                            name = state.loginField,
                            email = state.emailField,
                            password = state.passwordField
                        )
                    )) {
                        is Resource.Error<String> -> {
                            state = state.copy(loading = false)
                            SnackbarController.sendEvent(SnackbarEvent(message = result.message ?: "Unresolved Error"))
                        }

                        is Resource.Success<String> -> {
                            state = state.copy(loading = false)
                            backstack.add(VerificationCodeScreen)
                        }
                    }
                }
                LoginIntent.SendVerificationCode -> {
                    state = state.copy(loading = true)

                    when(val result = repository.verifyEmail(state.emailField, state.verificationCode)) {
                        is Resource.Error<Token> -> {
                            state = state.copy(loading = false)

                            SnackbarController.sendEvent(SnackbarEvent(message = result.message ?: "Unresolved Error"))
                        }
                        is Resource.Success<Token> -> {
                            // TODO: Encrypt and write access token and refresh token {
                            if(!tokenFile.exists()) {
                                tokenFile.createNewFile()
                            }
                            val fos = FileOutputStream(tokenFile)

                            // TODO }
                            state = state.copy(loading = false)
                            SnackbarController.sendEvent(SnackbarEvent(message = "Successfully Registered!"))
                            delay(4000)
                            backstack.clear()

                        }
                    }
                }
            }
        }
    }
}