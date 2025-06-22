package com.example.marketplace.presentation.viewmodels.loginviewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.marketplace.presentation.viewmodels.basemvipattern.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel() : BaseViewModel<LoginAction, LoginIntent, LoginState>() {

    override val state: MutableState<LoginState> = mutableStateOf(LoginState.Fields())
        private set

    override fun intentToAction(intent: LoginIntent): LoginAction {
        return when(intent) {
            LoginIntent.SendCredentials -> LoginAction.SendCredentials
            is LoginIntent.TypeInLoginField -> LoginAction.TypeInLoginField(intent.login)
            is LoginIntent.TypeInPasswordField -> LoginAction.TypeInPasswordField(intent.password)
        }
    }

    override fun handleAction(action: LoginAction) {
        viewModelScope.launch {
            when(action) {
                LoginAction.SendCredentials -> {

                }
                is LoginAction.TypeInLoginField -> {
                    
                }
                is LoginAction.TypeInPasswordField -> {

                }
            }
        }
    }

}