package com.example.marketplace.di

import com.example.marketplace.data.repository.LoginRepositoryImpl
import com.example.marketplace.data.repository.MarketplaceRepositoryImpl
import com.example.marketplace.domain.repository.LoginRepository
import com.example.marketplace.domain.repository.MarketplaceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMarketplaceRepository(marketplaceRepository: MarketplaceRepositoryImpl): MarketplaceRepository

    @Binds
    abstract fun bindLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository

}