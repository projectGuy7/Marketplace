package com.example.marketplace.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.marketplace.data.remote.LoginInterceptor
import com.example.marketplace.data.repository.HomeRepositoryImpl
import com.example.marketplace.di.HomeViewModelFactory
import com.example.marketplace.di.LoginViewModelFactory
import com.example.marketplace.di.createMarketplaceApi
import com.example.marketplace.presentation.screens.CodeVerification
import kotlinx.serialization.Serializable
import com.example.marketplace.presentation.screens.LogIn
import com.example.marketplace.presentation.viewmodels.homemvi.HomeState
import com.example.marketplace.presentation.viewmodels.homemvi.HomeViewModel
import com.example.marketplace.presentation.viewmodels.loginmvi.LoginViewModel
import java.io.File

@Serializable
data object LoginScreen: NavKey

@Serializable
data object VerificationCodeScreen: NavKey

@Serializable
data class HomeScreen(val state: HomeState): NavKey


@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    tokenFile: File
) {
    val backStack = rememberNavBackStack<NavKey>()
    if(!tokenFile.exists()) {
        backStack.add(LoginScreen)
    } else {
        backStack.add(HomeScreen(HomeState()))
    }
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
                is HomeScreen -> {
                    NavEntry(key) {


                        val homeViewModel: HomeViewModel = hiltViewModel(
                            creationCallback = { factory: HomeViewModelFactory ->
                                factory.run {
                                    createHomeViewModel(
                                        homeRepository = HomeRepositoryImpl(createMarketplaceApi(LoginInterceptor(
                                            accessToken = loginViewModel.state.token!!.accessToken,
                                            refreshToken = loginViewModel.state.token!!.refreshToken
                                        ))),
                                        backstack = backStack,
                                        homeState = key.state
                                    )
                                }
                            }
                        )


                    }
                }
                else -> throw Exception("NavKey not identified")
            }
        }
    )
}