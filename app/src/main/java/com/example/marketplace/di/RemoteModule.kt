package com.example.marketplace.di

import com.example.marketplace.data.remote.LoginInterceptor
import com.example.marketplace.data.remote.MarketplaceApi
import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @ApiWithoutInterceptor
    fun provideMarketplaceApi(): MarketplaceApi {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MarketplaceApi::class.java)
    }

    @Provides
    @ApiWithLoginInterceptor
    fun provideMarketplaceApiWithLoginInterceptor(interceptor: LoginInterceptor): MarketplaceApi {
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
}