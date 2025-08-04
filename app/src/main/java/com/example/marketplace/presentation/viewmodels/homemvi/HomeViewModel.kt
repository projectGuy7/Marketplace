package com.example.marketplace.presentation.viewmodels.homemvi

import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import com.example.marketplace.di.HomeViewModelFactory
import com.example.marketplace.di.LoginViewModelFactory
import com.example.marketplace.domain.marketplace.Item
import com.example.marketplace.domain.repository.HomeRepository
import com.example.marketplace.domain.util.Resource
import com.example.marketplace.presentation.SnackbarAction
import com.example.marketplace.presentation.SnackbarController
import com.example.marketplace.presentation.SnackbarEvent
import com.example.marketplace.presentation.viewmodels.basemvipattern.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = HomeViewModelFactory::class)
class HomeViewModel @AssistedInject constructor(
    @Assisted val homeRepository: HomeRepository,
    @Assisted val backstack: NavBackStack,
    @Assisted override var state: HomeState
): BaseViewModel<HomeIntent, HomeState>() {

    init {
        viewModelScope.launch {
            loadItems()
        }
    }

    suspend fun loadItems() {
        if(state.searchBar.isEmpty()) {
            when(val result = homeRepository.getItems()) {
                is Resource.Error<List<Item>> -> {
                    SnackbarController.sendEvent(SnackbarEvent(
                        message = "Couldn't load items",
                        action = SnackbarAction(name = "Try again", action = {
                            loadItems()
                        })
                    ))
                }
                is Resource.Success<List<Item>> -> {
                    state = state.copy(items = result.data!!)
                }
            }
        } else {
            when(val result = homeRepository.getItemsByName(state.searchBar)) {
                is Resource.Error<List<Item>> -> {
                    SnackbarController.sendEvent(SnackbarEvent(
                        message = "Couldn't load items for \"${state.searchBar}\"",
                        action = SnackbarAction(name = "Try again", action = {
                            loadItems()
                        })
                    ))
                }
                is Resource.Success<List<Item>> -> {
                    state = state.copy(items = result.data!!)
                }
            }
        }
    }

    override fun handleIntent(intent: HomeIntent) {
//        when(intent) {
//            HomeIntent.Search -> {
//
//
//            }
//            HomeIntent.EscapeSearchMod -> {
//
//            }
//            is HomeIntent.UpdateSearchBar -> {
//
//            }
//        }
    }
}

/**
 * single instances of HomeViewModel and HomeState are dedicated to each Screen, where first is for home screen with
 * default stuff and followings are for each screen with certain request (specified in searchBar).
 * ViewModels shall be created in NavigationRoot and SearchBar value should be provided initially through constructor.
 *
 */