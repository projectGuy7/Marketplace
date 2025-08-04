package com.example.marketplace.presentation.viewmodels.homemvi

import com.example.marketplace.domain.marketplace.Item
import com.example.marketplace.presentation.viewmodels.basemvipattern.ViewIntent

sealed class HomeIntent: ViewIntent {
    data class UpdateSearchBar(val searchBar: String): HomeIntent()
    data class AddToCart(val item: Item): HomeIntent()
    data object EscapeSearchMod: HomeIntent()
    data object Search: HomeIntent()
}