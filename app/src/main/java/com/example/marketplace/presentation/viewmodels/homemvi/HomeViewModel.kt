package com.example.marketplace.presentation.viewmodels.homemvi

import androidx.lifecycle.ViewModel
import com.example.marketplace.domain.repository.HomeRepository
import com.example.marketplace.presentation.viewmodels.basemvipattern.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class HomeViewModel @AssistedInject constructor(
    @Assisted homeRepository: HomeRepository
): BaseViewModel<HomeIntent, HomeState>() {
    override var state: HomeState = HomeState()

    override fun handleIntent(intent: HomeIntent) {
        TODO("Not yet implemented")
    }
}