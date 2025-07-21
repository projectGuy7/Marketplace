package com.example.marketplace.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.marketplace.di.LoginViewModelFactory
import com.example.marketplace.presentation.CodeVerification
import kotlinx.serialization.Serializable
import com.example.marketplace.presentation.LogIn
import com.example.marketplace.presentation.viewmodels.loginmvi.LoginViewModel

@Serializable
data object LoginScreen: NavKey

@Serializable
data object VerificationCodeScreen: NavKey


@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    appContext: Context
) {
    val backStack = rememberNavBackStack(LoginScreen)
    val loginViewModel: LoginViewModel = hiltViewModel(
        creationCallback = { factory: LoginViewModelFactory ->
            factory.createLoginViewModel(backStack)
        }
    )
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            when(key) {
                LoginScreen -> {
                    NavEntry(key) {
                        LogIn(modifier, loginViewModel.state, loginViewModel::handleIntent)
                    }
                }
                VerificationCodeScreen -> {
                    NavEntry(key) {
                        CodeVerification(modifier, loginViewModel.state, loginViewModel::handleIntent)
                    }
                }
                else -> throw Exception("NavKey not identified")
            }
        }
    )
}