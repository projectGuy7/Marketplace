package com.example.marketplace.di

import android.content.Context
import androidx.navigation3.runtime.NavBackStack
import com.example.marketplace.cryptomanager.CryptoManager
import com.example.marketplace.presentation.viewmodels.loginmvi.LoginViewModel
import dagger.assisted.AssistedFactory
import java.io.File

@AssistedFactory
interface LoginViewModelFactory {

    fun createLoginViewModel(backStack: NavBackStack, tokenFile: File, cryptoManager: CryptoManager): LoginViewModel

}