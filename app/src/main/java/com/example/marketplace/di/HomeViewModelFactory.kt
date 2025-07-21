package com.example.marketplace.di

import com.example.marketplace.data.remote.LoginInterceptor
import com.example.marketplace.data.remote.MarketplaceApi
import com.example.marketplace.data.repository.HomeRepositoryImpl
import com.example.marketplace.domain.repository.HomeRepository
import com.example.marketplace.presentation.viewmodels.homemvi.HomeViewModel
import dagger.assisted.AssistedFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@AssistedFactory
abstract class HomeViewModelFactory {

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

    abstract fun createHomeRepository(api: MarketplaceApi): HomeRepositoryImpl

    abstract fun createMarketplaceViewModel(homeRepository: HomeRepository): HomeViewModel
}