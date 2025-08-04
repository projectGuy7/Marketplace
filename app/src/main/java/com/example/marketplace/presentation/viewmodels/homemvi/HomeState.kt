package com.example.marketplace.presentation.viewmodels.homemvi

import com.example.marketplace.domain.marketplace.Item
import com.example.marketplace.presentation.viewmodels.basemvipattern.ViewState
import kotlinx.serialization.Serializable

@Serializable
data class HomeState(
    val searchBar: String = "",
    val items: List<Item> = emptyList(),
    val loading: Boolean = false
): ViewState