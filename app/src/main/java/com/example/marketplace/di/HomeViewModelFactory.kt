package com.example.marketplace.di

import androidx.navigation3.runtime.NavBackStack
import com.example.marketplace.data.remote.LoginInterceptor
import com.example.marketplace.data.remote.MarketplaceApi
import com.example.marketplace.domain.repository.HomeRepository
import com.example.marketplace.presentation.viewmodels.homemvi.HomeState
import com.example.marketplace.presentation.viewmodels.homemvi.HomeViewModel
import dagger.assisted.AssistedFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@AssistedFactory
interface HomeViewModelFactory {

    fun createHomeViewModel(
        homeRepository: HomeRepository,
        backstack: NavBackStack,
        homeState: HomeState = HomeState()
    ): HomeViewModel
}

@ApiWithLoginInterceptor
fun createMarketplaceApi(interceptor: LoginInterceptor): MarketplaceApi {
    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl("http://10.0.2.2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
        .create(MarketplaceApi::class.java)
}