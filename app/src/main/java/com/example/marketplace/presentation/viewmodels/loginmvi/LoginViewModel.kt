package com.example.marketplace.presentation.viewmodels.loginmvi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import com.example.marketplace.domain.marketplace.User
import com.example.marketplace.domain.repository.LoginRepository
import com.example.marketplace.domain.repository.MarketplaceRepository
import com.example.marketplace.domain.util.Resource
import com.example.marketplace.presentation.navigation.VerificationCodeScreen
import com.example.marketplace.presentation.viewmodels.basemvipattern.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository: LoginRepository,
    @Assisted val backstack: NavBackStack
) : BaseViewModel<LoginIntent, LoginState>() {

    override var state by mutableStateOf(LoginState())

    override fun handleIntent(intent: LoginIntent) {
        viewModelScope.launch {
            when(intent) {
                is LoginIntent.TypeInLoginField -> {
                    state = state.copy(loginField = intent.login)
                }
                is LoginIntent.TypeInPasswordField -> {
                    state = state.copy(passwordField = intent.password)
                }
                is LoginIntent.TypeInCodeVerificationField -> {
                    state = state.copy(passwordField = intent.codeVerification)
                }
                is LoginIntent.TypeInEmailField -> {
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
                            state = state.copy(loading = false, error = result.message)
                        }

                        is Resource.Success<String> -> {
                            state = state.copy(loading = false)
                            backstack.add(VerificationCodeScreen)
                        }
                    }
                }
                LoginIntent.SendVerificationCode -> {
//                    state = state.copy(loading = true)
//                    state = when(val result = repository.register(
//                        User(
//                            name = state.loginField,
//                            email = state.emailField,
//                            password = state.passwordField
//                        )
//                    )) {
//                        is Resource.Error<String> -> {
//                            state.copy(loading = false, error = result.message)
//                        }
//
//                        is Resource.Success<String> -> {
//                            state.copy(loading = false)
//                        }
//                    }
                }
            }
        }
    }

}