package com.example.marketplace.presentation.viewmodels.basemvipattern

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.marketplace.presentation.viewmodels.loginmvi.LoginState

abstract class BaseViewModel<INTENT: ViewIntent, STATE: ViewState>: ViewModel() {

    abstract var state: STATE
        protected set

    abstract fun handleIntent(intent: INTENT)

}