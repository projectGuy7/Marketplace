package com.example.marketplace.presentation.viewmodels.basemvipattern

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ACTION: ViewAction, INTENT: ViewIntent, STATE: ViewState>: ViewModel() {

    abstract val state: MutableState<STATE>
        protected set

    abstract fun intentToAction(intent: INTENT): ACTION

    abstract fun handleAction(action: ACTION)

}